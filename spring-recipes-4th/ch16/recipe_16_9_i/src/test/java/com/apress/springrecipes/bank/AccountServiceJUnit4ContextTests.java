package com.apress.springrecipes.bank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.apress.springrecipes.bank.config.BankConfiguration;

/**
 * Created by marten on 16-06-14.
 */
@ContextConfiguration(classes = BankConfiguration.class)
public class AccountServiceJUnit4ContextTests extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String TEST_ACCOUNT_NO = "1234";

    @Autowired
    private AccountService accountService;

    @Before
    public void init() {
        executeSqlScript("classpath:/bank.sql",true);
        jdbcTemplate.update(
                "INSERT INTO ACCOUNT (ACCOUNT_NO, BALANCE) VALUES (?, ?)",
                TEST_ACCOUNT_NO, 100);
    }

    @Test
    @Timed(millis = 1000)
    public void deposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        double balance = jdbcTemplate.queryForObject(
                "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?",
                Double.class, TEST_ACCOUNT_NO);
        assertEquals(balance, 150.0, 0);
    }

    @Test
    @Repeat(5)
    public void withDraw() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        double balance = jdbcTemplate.queryForObject(
                "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?",
                Double.class, TEST_ACCOUNT_NO);
        assertEquals(balance, 50.0, 0);
    }

}
