package com.apress.springrecipes.replicator;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.apress.springrecipes.replicator.config");

        FileReplicator documentReplicator = context.getBean(FileReplicator.class);

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("fileReplicator", documentReplicator);

        JobDetail job = JobBuilder.newJob(FileReplicationJob.class)
                .withIdentity("documentReplicationJob")
                .storeDurably()
                .usingJobData(jobDataMap)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("documentReplicationTrigger")
                .startAt(new Date(System.currentTimeMillis() + 5000))
                .forJob(job)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(60)
                        .repeatForever())
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }
}