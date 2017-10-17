package com.apress.springrecipes.replicator;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.apress.springrecipes.replicator.config");
        System.in.read();

    }
}
