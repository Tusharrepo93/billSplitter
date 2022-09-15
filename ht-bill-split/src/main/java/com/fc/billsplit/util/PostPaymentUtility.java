package com.fc.billsplit.util;

import com.fc.billsplit.dto.GroupDetailsDto;
import com.fc.billsplit.dto.UserExpenseDetailsDto;
import com.fc.billsplit.model.ExpenseTxnHistory;
import com.fc.billsplit.model.GroupExpense;
import com.fc.billsplit.model.UserGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostPaymentUtility {

  public static GroupDetailsDto convertGroupDetails(
      UserGroup userGroup, List<GroupExpense> groupExpense) {
    List<UserExpenseDetailsDto> userExpenseDetailsDtos = new ArrayList<>();
    for (int i = 0; i < groupExpense.size(); i++) {
      UserExpenseDetailsDto userExpenseDetailsDto =
          new UserExpenseDetailsDto(
              groupExpense.get(i).getUserName(),
              groupExpense.get(i).getMobileNo(),
              groupExpense.get(i).getAmount(),
              groupExpense.get(i).getIsAdmin());
      userExpenseDetailsDtos.add(userExpenseDetailsDto);
    }

    GroupDetailsDto groupDetailsDto = new GroupDetailsDto();
    groupDetailsDto.setGroupId(userGroup.getId());
    groupDetailsDto.setGroupName(userGroup.getGroupData());
    groupDetailsDto.setUserExpenseDetailsDto(userExpenseDetailsDtos);

    return groupDetailsDto;
  }

  public static void convertDtoEntityGroupExpense(
      UserExpenseDetailsDto userExpenseDetailsDto, GroupExpense groupExpense) {
      groupExpense.setAmount(groupExpense.getAmount()-userExpenseDetailsDto.getBalanceAmount());
  }

  public static List<ExpenseTxnHistory> convertDtoEntityExpenseTxn(
      List<UserExpenseDetailsDto> userExpenseDetailsDto, UserGroup userGroup, String mobNo) {
    List<ExpenseTxnHistory> lst = new ArrayList<>();

    for (int i = 0; i < userExpenseDetailsDto.size(); i++) {
      ExpenseTxnHistory expenseTxnHistory = new ExpenseTxnHistory();
      expenseTxnHistory.setAmount(userExpenseDetailsDto.get(i).getBalanceAmount());
      expenseTxnHistory.setMobile_no(userExpenseDetailsDto.get(i).getMobileNo());
      expenseTxnHistory.setAmount(userExpenseDetailsDto.get(i).getBalanceAmount());
      expenseTxnHistory.setUpdated_by_mob_no(mobNo);
      lst.add(expenseTxnHistory);
    }
    return lst;
  }
}
