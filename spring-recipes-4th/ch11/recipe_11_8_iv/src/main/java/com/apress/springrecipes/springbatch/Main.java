package com.apress.springrecipes.springbatch;

import com.apress.springrecipes.springbatch.config.BatchConfiguration;
import com.apress.springrecipes.springbatch.config.UserJob;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws Throwable {

        new AnnotationConfigApplicationContext(BatchConfiguration.class, UserJob.class);
    }
}
