package com.company.domain;

import java.math.BigDecimal;

public class BankAccount {

    private String id;
    private String currency;
    private BigDecimal balance;

    public BankAccount(String id, String currency, BigDecimal balance) {
        this.id = id;
        this.currency = currency;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return  "Számlaszám: " + id + '\n' +
                "Pénznem: " + currency + '\n' +
                "Egyenleg: " + balance;
    }
}
