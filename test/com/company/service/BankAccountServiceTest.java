package com.company.service;

import com.company.domain.BankAccount;
import com.company.domain.CardTransaction;
import org.junit.*;
import org.mockito.*;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

public class BankAccountServiceTest {

    @Spy
    BankAccountService service = spy(BankAccountService.class);

    @Test
    public void processTransactionWithValidAccount() {
        BankAccount testAccount = new BankAccount("test_id", "TEST", new BigDecimal(100));
        CardTransaction validTransaction =
                new CardTransaction(testAccount.getId(), testAccount.getCurrency(), new BigDecimal(-100), null);

        when(service.getBankAccountById(testAccount.getId())).thenReturn(Optional.of(testAccount));
        service.processTransaction(validTransaction);

        int entryCount = service.historyEntryCount();

        Assert.assertEquals(BigDecimal.ZERO, testAccount.getBalance());
        Assert.assertEquals(1, entryCount);
    }

    @Test
    public void processTransactionWithValidAccountFx() {
        BankAccount testAccount = new BankAccount("test_id", "HUF", new BigDecimal(1600));
        CardTransaction validTransaction =
                new CardTransaction(testAccount.getId(), "TEST", new BigDecimal(-4), new BigDecimal(300));

        when(service.getBankAccountById(testAccount.getId())).thenReturn(Optional.of(testAccount));
        service.processTransaction(validTransaction);

        int entryCount = service.historyEntryCount();

        Assert.assertEquals(new BigDecimal(400), testAccount.getBalance());
        Assert.assertEquals(1, entryCount);
    }

    @Test
    public void processTransactionWithInvalidAccount() {

        BankAccount testAccount = new BankAccount("test_id", "TEST", new BigDecimal(100));
        CardTransaction validTransaction =
                new CardTransaction(testAccount.getId(), testAccount.getCurrency(), new BigDecimal(-100), null);

        int entryCount = service.historyEntryCount();

        service.processTransaction(validTransaction);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(service).logWarning(argumentCaptor.capture());

        Assert.assertEquals(argumentCaptor.getValue(), testAccount.getId());
        Assert.assertEquals(new BigDecimal(100), testAccount.getBalance());
        Assert.assertEquals(0, entryCount);

    }
    @Test
    public void processTransactionWithMissingData() {

        BankAccount testAccount = new BankAccount("test_id", "TEST", new BigDecimal(100));
        CardTransaction validTransaction =
                new CardTransaction(testAccount.getId(), testAccount.getCurrency(), null, null);

        int entryCount = service.historyEntryCount();

        service.processTransaction(validTransaction);

        Assert.assertEquals(new BigDecimal(100), testAccount.getBalance());
        Assert.assertEquals(0, entryCount);

    }
}