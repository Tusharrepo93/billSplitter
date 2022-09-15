package com.fc.billsplit.service;

import com.fc.billsplit.dao.ExpenseTxnHistoryRepository;
import com.fc.billsplit.dao.GroupExpenseRepository;
import com.fc.billsplit.dao.UserGroupRepository;
import com.fc.billsplit.dto.GroupDetailsDto;
import com.fc.billsplit.dto.UserExpenseDetailsDto;
import com.fc.billsplit.model.GroupExpense;
import com.fc.billsplit.model.UserGroup;
import com.fc.billsplit.util.PostPaymentUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostPaymentServiceImpl implements PostPaymentService {

  @Autowired
  private GroupExpenseRepository groupExpenseRepository;

  @Autowired
  private UserGroupRepository userGroupRepository;

  @Autowired
  private ExpenseTxnHistoryRepository expenseTxnHistoryRepository;

  @Override
  public GroupDetailsDto getGroupDetails(int groupId) {

    if (groupId == 0) return null;

    Optional<UserGroup> userGroupOptional = userGroupRepository.findById(groupId);

    if (!userGroupOptional.isPresent()) return null;

    UserGroup userGroup = userGroupOptional.get();

    List<GroupExpense> groupExpenseList = groupExpenseRepository.findByUserGroupIdId(groupId);

    if (groupExpenseList.size() == 0) return null;

    return PostPaymentUtility.convertGroupDetails(userGroup, groupExpenseList);
  }

  @Override
  public String updateExpenseGroupDetails(GroupDetailsDto groupDetailsDto) {

    if (groupDetailsDto == null) return null;

    Optional<UserGroup> userGroupOptional =
        userGroupRepository.findById(groupDetailsDto.getGroupId());

    if (!userGroupOptional.isPresent()) return null;

    List<GroupExpense> groupExpenseList =
        groupExpenseRepository.findByUserGroupIdId(groupDetailsDto.getGroupId());

    if (groupExpenseList.size() == 0) return null;

    GroupExpense groupExpenseAdmin =
        groupExpenseList.stream()
            .filter(groupExpense -> groupExpense.getIsAdmin().equalsIgnoreCase("1"))
            .findFirst()
            .get();

    expenseTxnHistoryRepository.saveAll(
        PostPaymentUtility.convertDtoEntityExpenseTxn(
            groupDetailsDto.getUserExpenseDetailsDto(),
            userGroupOptional.get(),
            groupExpenseAdmin.getMobileNo()));

    for (int i = 0; i < groupDetailsDto.getUserExpenseDetailsDto().size(); i++) {
      for (int j = 0; j < groupExpenseList.size(); j++) {
        if (groupDetailsDto
            .getUserExpenseDetailsDto()
            .get(i)
            .getMobileNo()
            .equalsIgnoreCase(groupExpenseList.get(j).getMobileNo())) {
          if (groupExpenseList.get(j).getIsAdmin().equalsIgnoreCase("0"))
            PostPaymentUtility.convertDtoEntityGroupExpense(
                groupDetailsDto.getUserExpenseDetailsDto().get(i), groupExpenseList.get(j));
        }
      }
    }
    groupExpenseAdmin.setAmount(
        groupExpenseAdmin.getAmount() + getTotalAmountPostPayment(groupDetailsDto.getUserExpenseDetailsDto()));
    groupExpenseRepository.saveAll(groupExpenseList);

    return "SUCCESS";
  }

  private Double getTotalAmountPostPayment(List<UserExpenseDetailsDto> userExpenseDetailsDtos) {
    double amountTotal = 0.0;
    for (int i = 0; i < userExpenseDetailsDtos.size(); i++) {
      if (userExpenseDetailsDtos.get(i).getIsAdmin().equalsIgnoreCase("0"))
        amountTotal += userExpenseDetailsDtos.get(i).getBalanceAmount();
    }
    return amountTotal;
  }
}
