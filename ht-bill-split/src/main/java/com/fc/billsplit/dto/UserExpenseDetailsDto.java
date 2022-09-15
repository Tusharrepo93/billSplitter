package com.fc.billsplit.dto;

import java.math.BigDecimal;

public class UserExpenseDetailsDto {

    private String userName;
    private String mobileNo;
    private double balanceAmount;
    private String isAdmin;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public UserExpenseDetailsDto(String userName, String mobileNo, double balanceAmount, String isAdmin) {
        this.userName = userName;
        this.mobileNo = mobileNo;
        this.balanceAmount = balanceAmount;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "UserExpenseDetailsDto{" +
                "userName='" + userName + '\'' +
                ", mobileNo=" + mobileNo +
                ", balanceAmount=" + balanceAmount +
                ", isAdmin='" + isAdmin + '\'' +
                '}';
    }
}
