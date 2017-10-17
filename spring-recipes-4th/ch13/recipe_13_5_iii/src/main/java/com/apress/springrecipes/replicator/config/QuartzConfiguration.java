package com.apress.springrecipes.replicator.config;

import java.util.Collections;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.apress.springrecipes.replicator.FileReplicationJob;
import com.apress.springrecipes.replicator.FileReplicator;

/**
 * Created by marten on 26-05-14.
 */
@Configuration
public class QuartzConfiguration {

    @Bean
    @Autowired
    public JobDetailFactoryBean documentReplicationJob(FileReplicator fileReplicator) {
        JobDetailFactoryBean documentReplicationJob = new JobDetailFactoryBean();
        documentReplicationJob.setJobClass(FileReplicationJob.class);
        documentReplicationJob.setDurability(true);
        documentReplicationJob.setJobDataAsMap(Collections.singletonMap("fileReplicator", fileReplicator));
        return documentReplicationJob;
    }

//    @Bean
//    @Autowired
//    public MethodInvokingJobDetailFactoryBean documentReplicationJob(FileReplicator fileReplicator) {
//        MethodInvokingJobDetailFactoryBean documentReplicationJob = new MethodInvokingJobDetailFactoryBean();
//        documentReplicationJob.setTargetObject(fileReplicator);
//        documentReplicationJob.setTargetMethod("replicatie");
//        return documentReplicationJob;
//    }

    @Bean
    @Autowired
    public SimpleTriggerFactoryBean documentReplicationTrigger(JobDetail documentReplicationJob) {
        SimpleTriggerFactoryBean documentReplicationTrigger = new SimpleTriggerFactoryBean();
        documentReplicationTrigger.setJobDetail(documentReplicationJob);
        documentReplicationTrigger.setStartDelay(5000);
        documentReplicationTrigger.setRepeatInterval(60000);
        return documentReplicationTrigger;
    }

    @Bean
    @Autowired
    public SchedulerFactoryBean scheduler(Trigger[] triggers) {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(triggers);
        return scheduler;
    }
}
