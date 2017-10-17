package com.apress.springrecipes.executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class SpringExecutorsDemo {

    @Autowired
    private SimpleAsyncTaskExecutor asyncTaskExecutor;
    @Autowired
    private SyncTaskExecutor syncTaskExecutor;
    @Autowired
    private TaskExecutorAdapter taskExecutorAdapter;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private DemonstrationRunnable task;

    @PostConstruct
    public void submitJobs() {
        syncTaskExecutor.execute(task);
        taskExecutorAdapter.submit(task);
        asyncTaskExecutor.submit(task);
                      
                      /* will do 100 at a time, 
                             then queue the rest, ie,
                             should take round 5 seconds total 
                         */
        for (int i = 0; i < 500; i++)
            threadPoolTaskExecutor.submit(task);
    }

    public static void main(String[] args) {

        new AnnotationConfigApplicationContext(ExecutorsConfiguration.class)
                .registerShutdownHook();
    }

}