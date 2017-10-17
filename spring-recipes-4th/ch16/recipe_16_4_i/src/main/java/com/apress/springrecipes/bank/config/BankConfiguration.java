package com.apress.springrecipes.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.springrecipes.bank.AccountServiceImpl;
import com.apress.springrecipes.bank.InMemoryAccountDao;

@Configuration
public class BankConfiguration {

    @Bean
    public InMemoryAccountDao accountDao() {
        return new InMemoryAccountDao();
    }

    @Bean
    public AccountServiceImpl accountService() {
        return new AccountServiceImpl(accountDao());
    }
}
