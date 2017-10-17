package com.apress.springrecipes.bookshop;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.bookshop.config.BookstoreConfiguration;

public class Main {

    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new AnnotationConfigApplicationContext(BookstoreConfiguration.class);

        Cashier cashier = context.getBean(Cashier.class);
        List<String> isbnList = Arrays.asList("0001", "0002");
        cashier.checkout(isbnList, "user1");

    }
}
