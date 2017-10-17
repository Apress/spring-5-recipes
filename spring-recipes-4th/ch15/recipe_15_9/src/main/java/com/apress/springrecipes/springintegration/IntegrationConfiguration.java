package com.apress.springrecipes.springintegration;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.integration.launch.JobLaunchingMessageHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;

public class IntegrationConfiguration {

    @Bean
    public FileToJobLaunchRequestTransformer transformer(Job job) {
        return new FileToJobLaunchRequestTransformer(job, "filename");
    }

    @Bean
    public JobLaunchingMessageHandler jobLaunchingMessageHandler(JobLauncher jobLauncher) {
        return new JobLaunchingMessageHandler(jobLauncher);
    }

    @Bean
    public IntegrationFlow fileToBatchFlow(@Value("file:${user.home}/customerstoimport/new/") File directory, FileToJobLaunchRequestTransformer transformer, JobLaunchingMessageHandler handler) {
        return IntegrationFlows
                    .from(Files.inboundAdapter(directory).patternFilter("customers-*.txt"), c -> c.poller(Pollers.fixedRate(10, TimeUnit.SECONDS)))
                    .transform(transformer)
                    .handle(handler)
                .get();

    }

}
