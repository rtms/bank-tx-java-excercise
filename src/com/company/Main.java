package com.company;

import com.company.domain.CardTransaction;
import com.company.service.BankAccountService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // create dummy data
        List<CardTransaction> transactionList = Arrays.asList(
            new CardTransaction("11111111-22222222", "HUF", BigDecimal.valueOf(-30000), null),
            new CardTransaction("11111111-22222222", "USD", BigDecimal.valueOf(-49.99), BigDecimal.valueOf(257.21)),
            new CardTransaction("22222222-33333333", "USD", BigDecimal.valueOf(-21), null),
            new CardTransaction("22222222-33333333", "CAD", BigDecimal.valueOf(129.90), BigDecimal.valueOf(1.25))
        );

        List<CardTransaction> transactions = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            transactions.add(transactionList.get(random.nextInt(4)));
        }
        transactions.add(new CardTransaction("asad", "0", null, null));

        // process all transactions
        BankAccountService service = new BankAccountService();

        int counter = 0;
        for (CardTransaction transaction : transactions) {
            service.processTransaction(transaction);

            if (counter++ % 10 == 0) {
                service.printAccountReport();
            }
        }

        if (args.length > 0) {
            // TODO manage input file as parameter
            System.out.println(args[0]);
        }
    }

}
