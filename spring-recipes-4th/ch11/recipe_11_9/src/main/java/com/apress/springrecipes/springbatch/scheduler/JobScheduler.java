package com.apress.springrecipes.springbatch.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobScheduler {

    private final JobLauncher jobLauncher;
    private final Job job;

    public JobScheduler(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public void runRegistrationsJob(Date date) throws Throwable {
        System.out.println("Starting job at " + date.toString());

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("date", date);
        jobParametersBuilder.addString("input.file", "registrations");

        JobParameters jobParameters = jobParametersBuilder.toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        System.out.println("jobExecution finished, exit code: " + jobExecution.getExitStatus().getExitCode());
    }

    @Scheduled(fixedDelay = 1000 * 10)
    public void runRegistrationsJobOnASchedule() throws Throwable {
        runRegistrationsJob(new Date());
    }

}
