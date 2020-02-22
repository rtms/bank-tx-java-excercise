package com.company.domain;

import java.math.BigDecimal;

public class BankAccount {

    private String accountNo;
    private String currency;
    private BigDecimal balance;

    public BankAccount(String accountNo, String currency, BigDecimal balance) {
        this.accountNo = accountNo;
        this.currency = currency;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return  "Számlaszám: " + accountNo + '\n' +
                "Pénznem: " + currency + '\n' +
                "Egyenleg: " + balance;
    }
}
