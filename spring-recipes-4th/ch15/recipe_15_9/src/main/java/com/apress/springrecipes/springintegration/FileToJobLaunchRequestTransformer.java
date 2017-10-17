package com.apress.springrecipes.springintegration;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.integration.annotation.Transformer;

public class FileToJobLaunchRequestTransformer {

    private final Job job;
    private final String fileParameterName;

    public FileToJobLaunchRequestTransformer(Job job, String fileParameterName) {
        this.job=job;
        this.fileParameterName=fileParameterName;
    }

    @Transformer
    public JobLaunchRequest transform(File file) throws Exception {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addString(fileParameterName, file.getAbsolutePath());
        return new JobLaunchRequest(job, builder.toJobParameters());
    }
}
