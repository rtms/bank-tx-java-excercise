package com.company.domain;

import java.math.BigDecimal;

public class CardTransaction {

    private String accountId;
    private String currency;
    private BigDecimal amount;
    private BigDecimal currencyRate;

    public CardTransaction(String accountId, String currency, BigDecimal amount, BigDecimal currencyRate) {
        this.accountId = accountId;
        this.currency = currency;
        this.amount = amount;
        this.currencyRate = currencyRate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    @Override
    public String toString() {
        return  "Számlaszám: " + accountId + '\n' +
                "Pénznem: " + currency + '\n' +
                "Összeg: " + amount + '\n' +
                "Valutaárfolyam: " + currencyRate;
    }
}
