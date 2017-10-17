package com.apress.springrecipes.bookshop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.bookshop.config.BookstoreConfiguration;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BookstoreConfiguration.class);

        final BookShop bookShop = context.getBean(BookShop.class);

        Thread thread1 = new Thread(() -> {
            try {
                bookShop.increaseStock("0001", 5);
            } catch (RuntimeException e) {}

        }, "Thread 1");

        Thread thread2 = new Thread(() -> bookShop.checkStock("0001"), "Thread 2");

        thread1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {}
        thread2.start();

    }
}
