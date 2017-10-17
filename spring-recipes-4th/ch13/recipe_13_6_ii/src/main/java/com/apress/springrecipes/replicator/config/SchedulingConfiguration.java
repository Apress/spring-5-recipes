package com.apress.springrecipes.replicator.config;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.apress.springrecipes.replicator.FileReplicator;

@Configuration
@EnableScheduling
public class SchedulingConfiguration implements SchedulingConfigurer {

    @Autowired
    private FileReplicator fileReplicator;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(scheduler());
        taskRegistrar.addFixedDelayTask(() -> {
            try {
                fileReplicator.replicate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 60000);
    }

    @Bean
    public Executor scheduler() {
        return Executors.newScheduledThreadPool(10);
    }

}
