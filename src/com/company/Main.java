package com.company;

import com.company.domain.BankAccount;
import com.company.domain.CardTransaction;

import java.math.BigDecimal;

public class Main {

    // init hard-coded data
    static BankAccount firstAccount = new BankAccount(
            "11111111-22222222",
            "HUF",
            BigDecimal.valueOf(150000)
    );

    static BankAccount secondAccount = new BankAccount(
            "11111111-22222222",
            "HUF",
            BigDecimal.valueOf(150000)
    );

    static CardTransaction transaction1 = new CardTransaction(
            "11111111-22222222",
            "HUF",
            BigDecimal.valueOf(-30000),
            null
    );
    static CardTransaction transaction2 = new CardTransaction(
            "11111111-22222222",
            "USD",
            BigDecimal.valueOf(-49.99),
            BigDecimal.valueOf(257.21)
    );
    static CardTransaction transaction3 = new CardTransaction(
            "22222222-33333333",
            "USD",
            BigDecimal.valueOf(-21),
            null
    );
    static CardTransaction transaction4 = new CardTransaction(
            "22222222-33333333",
            "CAD",
            BigDecimal.valueOf(129.90),
            BigDecimal.valueOf(1.25)
    );

    public static void main(String[] args) {

        System.out.println(firstAccount);
        System.out.println(" - ");
        System.out.println(transaction1);

        if (args.length > 0) {
            // TODO manage input file as parameter
            System.out.println(args[0]);
        }
    }
}
