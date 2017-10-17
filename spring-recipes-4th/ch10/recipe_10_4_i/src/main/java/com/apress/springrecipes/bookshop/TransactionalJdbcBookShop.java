package com.apress.springrecipes.bookshop;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


public class TransactionalJdbcBookShop extends JdbcDaoSupport implements BookShop {

    private PlatformTransactionManager transactionManager;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void purchase(final String isbn, final String username) {
        TransactionTemplate transactionTemplate =
                new TransactionTemplate(transactionManager);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            protected void doInTransactionWithoutResult(
                    TransactionStatus status) {

                int price = getJdbcTemplate().queryForObject(
                        "SELECT PRICE FROM BOOK WHERE ISBN = ?", Integer.class, isbn);

                getJdbcTemplate().update(
                        "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", isbn );

                getJdbcTemplate().update(
                        "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", price, username);
            }
        });
    }


}
