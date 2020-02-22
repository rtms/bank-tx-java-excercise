package com.company;

import com.company.domain.CardTransaction;
import com.company.service.BankAccountService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        BankAccountService service = new BankAccountService();

        if (args.length > 0) {
            String inputFile = args[0];
            // read and validate card transactions from .csv file
            List<CardTransaction> cardTransactions = service.readAllValidTransactions(inputFile);

            // process transactions
            for (CardTransaction transaction : cardTransactions) {
                service.processTransaction(transaction);

                if (service.historyEntryCount() % 10 == 0) {
                    System.out.println("\nTranzakció riport");
                    System.out.println("Feldolgozott tranzakciók száma: " + service.historyEntryCount());
                    service.printAccountReport();
                }
            }
        } else {
            System.out.println(
                    "\nKérjük, adja meg a beolvasni kívánt .csv fájl nevét futtatási paraméterként!\n" +
                            "Például: \"java -jar bank-tx-java-excercise.jar input.csv\"\n" +
                            "Ügyeljen arra, hogy a .jar fájl és a .csv fájl azonos elérési úton legyen!\n");
        }
    }
}
