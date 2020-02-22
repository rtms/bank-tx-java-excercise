package com.company.service;


import com.company.domain.BankAccount;
import com.company.domain.CardTransaction;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class BankAccountService {

    static Logger logger = Logger.getLogger(BankAccountService.class.getName());

    // init hard-coded data
    public static List<BankAccount> accounts = Arrays.asList(
            new BankAccount("11111111-22222222","HUF", BigDecimal.valueOf(150000)),
            new BankAccount("22222222-33333333","USD", BigDecimal.valueOf(1230))
    );

    public void printTransactions(List<CardTransaction> transactions) {
        for (CardTransaction transaction : transactions) {
            System.out.println(transaction);
            System.out.println(" - ");
        }
    }

    public void processTransaction(CardTransaction transaction) {
        Optional<BankAccount> account = getBankAccountById(transaction.getAccountId());
        if (!account.isPresent()) {
            logger.warning("Nem létezik ilyen számlaszám: " + transaction.getAccountId());
        } else {
            BankAccount bankAccount = account.get();
            BigDecimal convertedAmount = transaction.getAmount();
            if (transaction.getCurrency().equals(bankAccount.getCurrency())) {
                convertedAmount = transaction.getAmount().multiply(transaction.getCurrencyRate());
            }
            bankAccount.setBalance(bankAccount.getBalance().add(convertedAmount));
        }
    }

    public Optional<BankAccount> getBankAccountById(String id) {
        return accounts.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    public void printAccountReport() {
        System.out.println("print report - ");
    }
}
