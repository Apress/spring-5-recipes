package com.apress.springrecipes.springbatch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;


public class HoroscopeDecider implements JobExecutionDecider {


    private boolean isMercuryIsInRetrograde () { return Math.random() > .9 ; }

    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        if (isMercuryIsInRetrograde()) {
            return FlowExecutionStatus.FAILED;
        }

        return FlowExecutionStatus.COMPLETED;
    }
}
