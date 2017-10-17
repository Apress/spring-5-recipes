package com.apress.springrecipes.bank;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.apress.springrecipes.bank.config.BankConfiguration;

/**
 * Created by marten on 16-06-14.
 */
@ContextConfiguration(classes = BankConfiguration.class)
public class AccountServiceJUnit4ContextTests extends AbstractJUnit4SpringContextTests {

    private static final String TEST_ACCOUNT_NO = "1234";

    private AccountService accountService;


    @Before
    public void init() {
        accountService = applicationContext.getBean(AccountService.class);
        accountService.createAccount(TEST_ACCOUNT_NO);
        accountService.deposit(TEST_ACCOUNT_NO, 100);
    }

    @Test
    public void deposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 150, 0);
    }

    @Test
    public void withDraw() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 50, 0);
    }

    @After
    public void cleanup() {
        accountService.removeAccount(TEST_ACCOUNT_NO);
    }

}
