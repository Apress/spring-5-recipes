package com.apress.springrecipes.bank;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class AccountServiceImplMockTests {

    private static final String TEST_ACCOUNT_NO = "1234";

    private AccountDao accountDao;
    private AccountService accountService;

    @Before
    public void init() {
        accountDao = mock(AccountDao.class);
        accountService = new AccountServiceImpl(accountDao);
    }

    @Test
    public void deposit() {
        // Setup
        Account account = new Account(TEST_ACCOUNT_NO, 100);
        when(accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

        // Execute
        accountService.deposit(TEST_ACCOUNT_NO, 50);

        // Verify
        verify(accountDao, times(1)).findAccount(any(String.class));
        verify(accountDao, times(1)).updateAccount(account);

    }

    @Test
    public void withdrawWithSufficientBalance() {
        // Setup
        Account account = new Account(TEST_ACCOUNT_NO, 100);
        when(accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

        // Execute
        accountService.withdraw(TEST_ACCOUNT_NO, 50);

        // Verify
        verify(accountDao, times(1)).findAccount(any(String.class));
        verify(accountDao, times(1)).updateAccount(account);

    }

    @Test(expected = InsufficientBalanceException.class)
    public void testWithdrawWithInsufficientBalance() {
        // Setup
        Account account = new Account(TEST_ACCOUNT_NO, 100);
        when(accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

        // Execute
        accountService.withdraw(TEST_ACCOUNT_NO, 150);
    }
}
