package com.apress.springrecipes.springbatch;

import com.apress.springrecipes.springbatch.config.BatchConfiguration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Throwable {

        ApplicationContext context = new AnnotationConfigApplicationContext(BatchConfiguration.class);

        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
        Job job = context.getBean("dailySalesFigures", Job.class);

        jobLauncher.run(job, new JobParametersBuilder()
                                .addDate( "date", new Date() ).toJobParameters());
    }
}
