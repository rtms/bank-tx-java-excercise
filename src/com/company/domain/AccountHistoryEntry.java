package com.company.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AccountHistoryEntry {
    private String accountId;
    private BigDecimal transactionAmount;
    private BigDecimal balance;

    public AccountHistoryEntry(String accountId, BigDecimal transactionAmount, BigDecimal balance) {
        this.accountId = accountId;
        this.transactionAmount = transactionAmount;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Tranzakció részletei: " +
                "Számlaszám: " + accountId +
                " | Összeg: " + transactionAmount.setScale(2, RoundingMode.CEILING) +
                " | Egyenleg: " + balance.setScale(2, RoundingMode.CEILING);
    }
}
