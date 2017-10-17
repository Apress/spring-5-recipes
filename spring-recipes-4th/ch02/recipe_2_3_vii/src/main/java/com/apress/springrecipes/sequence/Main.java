package com.apress.springrecipes.sequence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.sequence.config.SequenceConfiguration;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SequenceConfiguration.class);
        context.refresh();

        SequenceGenerator generator =
                (SequenceGenerator) context.getBean("sequenceGenerator");

        System.out.println(generator.getSequence());
        System.out.println(generator.getSequence());
    }
}
