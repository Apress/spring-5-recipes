package com.apress.springrecipes.bank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountServiceImplStubTests {

    private static final String TEST_ACCOUNT_NO = "1234";
    private AccountDaoStub accountDaoStub;
    private AccountService accountService;


    @Before
    public void init() {
        accountDaoStub = new AccountDaoStub();
        accountDaoStub.accountNo = TEST_ACCOUNT_NO;
        accountDaoStub.balance = 100;
        accountService = new AccountServiceImpl(accountDaoStub);
    }

    @Test
    public void deposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        assertEquals(accountDaoStub.accountNo, TEST_ACCOUNT_NO);
        assertEquals(accountDaoStub.balance, 150, 0);
    }

    @Test
    public void withdrawWithSufficientBalance() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        assertEquals(accountDaoStub.accountNo, TEST_ACCOUNT_NO);
        assertEquals(accountDaoStub.balance, 50, 0);
    }

    @Test(expected = InsufficientBalanceException.class)
    public void withdrawWithInsufficientBalance() {
        accountService.withdraw(TEST_ACCOUNT_NO, 150);
    }

    /**
     * Partially implemented stub implementation for the {@code AccountDao}
     */
    private static class AccountDaoStub implements AccountDao {

        private String accountNo;
        private double balance;

        public void createAccount(Account account) {}
        public void removeAccount(Account account) {}

        public Account findAccount(String accountNo) {
            return new Account(this.accountNo, this.balance);
        }

        public void updateAccount(Account account) {
            this.accountNo = account.getAccountNo();
            this.balance = account.getBalance();
        }
    }

}
