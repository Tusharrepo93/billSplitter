package com.fc.billsplit.service;

import com.fc.billsplit.dto.GroupDetailsDto;

public interface PostPaymentService {
    GroupDetailsDto getGroupDetails(int groupId);

    String updateExpenseGroupDetails(GroupDetailsDto groupDetailsDto);
}
