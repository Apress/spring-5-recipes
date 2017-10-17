package com.apress.springrecipes.springbatch;

import com.apress.springrecipes.springbatch.config.BatchConfiguration;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new AnnotationConfigApplicationContext(BatchConfiguration.class);

        JobRegistry jobRegistry = context.getBean(JobRegistry.class);
        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
        JobRepository jobRepository = context.getBean(JobRepository.class);

        System.out.println("JobRegistry: " + jobRegistry);
        System.out.println("JobLauncher: " + jobLauncher);
        System.out.println("JobRepository: " + jobRepository);


    }
}
