package com.company.domain;

import java.math.BigDecimal;

public class CardTransaction {

    private String accountNo;
    private String currency;
    private BigDecimal amount;
    private BigDecimal currencyRate;

    public CardTransaction(String accountNo, String currency, BigDecimal amount, BigDecimal currencyRate) {
        this.accountNo = accountNo;
        this.currency = currency;
        this.amount = amount;
        this.currencyRate = currencyRate;
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
        return  "Számlaszám: " + accountNo + '\n' +
                "Pénznem: " + currency + '\n' +
                "Összeg: " + amount + '\n' +
                "Valutaárfolyam: " + currencyRate;
    }
}
