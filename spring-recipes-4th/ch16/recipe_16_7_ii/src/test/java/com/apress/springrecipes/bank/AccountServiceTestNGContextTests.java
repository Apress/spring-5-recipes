package com.apress.springrecipes.bank;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.apress.springrecipes.bank.config.BankConfiguration;

/**
 * Created by marten on 16-06-14.
 */
@ContextConfiguration(classes = BankConfiguration.class)
@Sql(scripts="classpath:/bank.sql")
public class AccountServiceTestNGContextTests extends AbstractTransactionalTestNGSpringContextTests {

    private static final String TEST_ACCOUNT_NO = "1234";

    @Autowired
    private AccountService accountService;

    @BeforeMethod
    public void init() {
        jdbcTemplate.update(
                "INSERT INTO ACCOUNT (ACCOUNT_NO, BALANCE) VALUES (?, ?)",
                TEST_ACCOUNT_NO, 100);
    }

    @Test
    public void deposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        double balance = jdbcTemplate.queryForObject(
                "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?",
                Double.class, TEST_ACCOUNT_NO);
        assertEquals(balance, 150.0, 0);
    }

    @Test
    public void withDraw() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        double balance = jdbcTemplate.queryForObject(
                "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?",
                Double.class, TEST_ACCOUNT_NO);
        assertEquals(balance, 50.0, 0);
    }

}
