package com.apress.springrecipes.bookshop.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import com.apress.springrecipes.bookshop.TransactionalJdbcBookShop;

@Configuration
public class BookstoreConfiguration {

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(org.postgresql.Driver.class.getName());
        dataSource.setUrl("jdbc:postgresql://localhost:5432/bookstore");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public TransactionalJdbcBookShop bookShop(DataSource dataSource, PlatformTransactionManager transactionManager) {
        TransactionalJdbcBookShop bookShop = new TransactionalJdbcBookShop();
        bookShop.setDataSource(dataSource);
        bookShop.setTransactionManager(transactionManager);
        return bookShop;
    }
}
