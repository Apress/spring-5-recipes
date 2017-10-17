package com.apress.springrecipes.replicator;

import java.io.IOException;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class FileReplicationJob implements Job {

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        Map<String, Object> dataMap = context.getJobDetail().getJobDataMap();
        FileReplicator fileReplicator =
            (FileReplicator) dataMap.get("fileReplicator");
        try {
            fileReplicator.replicate();
        } catch (IOException e) {
            throw new JobExecutionException(e);
        }
    }
}