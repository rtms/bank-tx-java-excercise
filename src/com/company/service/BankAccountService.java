package com.company.service;

import com.company.Main;
import com.company.domain.AccountHistoryEntry;
import com.company.domain.BankAccount;
import com.company.domain.CardTransaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BankAccountService {

    static final Logger LOG = Logger.getLogger(BankAccountService.class.getName());

    // init hard-coded data
    private final static List<BankAccount> ACCOUNTS = Arrays.asList(
            new BankAccount("11111111-22222222","HUF", BigDecimal.valueOf(150000)),
            new BankAccount("22222222-33333333","USD", BigDecimal.valueOf(1230))
    );

    private final static List<AccountHistoryEntry> HISTORY = new ArrayList<>();

    public int historyEntryCount() {
        return HISTORY.size();
    }

    public void processTransaction(CardTransaction transaction) {
        Optional<BankAccount> account = getBankAccountById(transaction.getAccountId());
        if (!account.isPresent()) {
            LOG.warning("Nem létezik ilyen számlaszám: " + transaction.getAccountId());
        } else {
            BankAccount bankAccount = account.get();
            BigDecimal convertedAmount = transaction.getAmount();
            if (transaction.getCurrencyRate() != null && !transaction.getCurrency().equals(bankAccount.getCurrency())) {
                convertedAmount = transaction.getAmount().multiply(transaction.getCurrencyRate());
            }
            bankAccount.setBalance(bankAccount.getBalance().add(convertedAmount));

            HISTORY.add(new AccountHistoryEntry(bankAccount.getId(), convertedAmount, bankAccount.getBalance()));
        }
    }

    public Optional<BankAccount> getBankAccountById(String id) {
        return ACCOUNTS.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    public void printAccountReport() {
        Map<String, List<AccountHistoryEntry>> entries = HISTORY.stream()
            .collect(Collectors.groupingBy(AccountHistoryEntry::getAccountId));

        for (String id : entries.keySet()) {
            System.out.println("\nSzámlaszám: " + id + "\n");
            entries.get(id).forEach(System.out::println);
        }
    }

    public List<CardTransaction> readAllValidTransactions(String inputFile) {
        String jarPath = "";
        List<CardTransaction> transactions = new ArrayList<>();

        try {
            jarPath = new File(Main.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI())
                    .getParentFile()
                    .getAbsolutePath();
        } catch (URISyntaxException e) {
            System.out.println("Fájl megnyitása sikertelen!");
        }
        File file = new File(jarPath + "\\" + inputFile);
        if (!file.exists()) {
            System.out.println("A megadott fájl nem található!");
            return transactions;
        }

        try (Scanner scanner = new Scanner(file)) {
            int counter = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.print("reading line " + ++counter + "\t...... ");
                String[] template = {"", "", "", ""};

                String[] data = line.split(";");
                for (int i = 0; i < data.length; i++) {
                    template[i] = data[i];
                }
                CardTransaction transaction = new CardTransaction();
                if (!template[0].isEmpty() && !template[1].isEmpty() && !template[2].isEmpty()) {
                    transaction.setAccountId(template[0]);
                    transaction.setCurrency(template[1]);
                    try {
                        transaction.setAmount(new BigDecimal(Double.parseDouble(template[2])));
                        if (!template[3].isEmpty()) {
                            transaction.setCurrencyRate(new BigDecimal(Double.parseDouble(template[3])));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error");
                        continue;
                    }
                    System.out.println("ok");
                    transactions.add(transaction);
                } else {
                    System.out.println("error");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("A megadott fájl nem található!");
        }
        return transactions;
    }
}
