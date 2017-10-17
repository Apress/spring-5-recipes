package com.apress.springrecipes.bank.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.apress.springrecipes.bank.config.BankConfiguration;
import com.apress.springrecipes.bank.web.config.BankWebConfiguration;

@ContextConfiguration(classes= { BankWebConfiguration.class, BankConfiguration.class})
@WebAppConfiguration
@Sql(scripts ="classpath:/bank.sql")
public class DepositControllerJUnit4ContextTests extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String ACCOUNT_PARAM = "accountNo";
    private static final String AMOUNT_PARAM = "amount";

    private static final String TEST_ACCOUNT_NO = "1234";
    private static final String TEST_AMOUNT = "50.0";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void init() {
        jdbcTemplate.update(
                "INSERT INTO ACCOUNT (ACCOUNT_NO, BALANCE) VALUES (?, ?)",
                TEST_ACCOUNT_NO, 100);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }


    @Test
    public void deposit() throws Exception {
        mockMvc.perform(
                get("/deposit.do")
                        .param(ACCOUNT_PARAM, TEST_ACCOUNT_NO)
                        .param(AMOUNT_PARAM, TEST_AMOUNT))
                .andDo(print())
                .andExpect(forwardedUrl("/WEB-INF/views/success.jsp"))
                .andExpect(status().is(200));
    }
}
