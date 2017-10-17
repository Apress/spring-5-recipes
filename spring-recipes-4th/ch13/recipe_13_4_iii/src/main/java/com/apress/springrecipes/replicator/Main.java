package com.apress.springrecipes.replicator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

 
public class Main {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.apress.springrecipes.replicator.config");

        ErrorNotifier errorNotifier = context.getBean(ErrorNotifier.class);
        errorNotifier.notifyCopyError("c:/documents", "d:/documents", "spring_15_4_iii.doc");
    }
}