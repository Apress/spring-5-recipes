package com.apress.springrecipes.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.springrecipes.bank.AccountDao;
import com.apress.springrecipes.bank.AccountService;
import com.apress.springrecipes.bank.AccountServiceImpl;
import com.apress.springrecipes.bank.InMemoryAccountDao;

/**
 * Created by marten on 16-06-14.
 */
@Configuration
public class BankConfiguration {

    @Bean
    public AccountDao accountDao() {
        return new InMemoryAccountDao();
    }

    @Bean
    public AccountService accountService() {
        return new AccountServiceImpl(accountDao());
    }
}
