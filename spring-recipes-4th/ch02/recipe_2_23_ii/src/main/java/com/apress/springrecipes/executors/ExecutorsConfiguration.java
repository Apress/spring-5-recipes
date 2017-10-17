package com.apress.springrecipes.executors;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan
public class ExecutorsConfiguration {

    @Bean
    public TaskExecutorAdapter taskExecutorAdapter() {

        return new TaskExecutorAdapter(Executors.newCachedThreadPool());
    }

    @Bean
    public SimpleAsyncTaskExecutor simpleAsyncTaskExecutor() {

        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public SyncTaskExecutor syncTaskExecutor() {
        return new SyncTaskExecutor();
    }

    @Bean
    public ScheduledExecutorFactoryBean scheduledExecutorFactoryBean(ScheduledExecutorTask scheduledExecutorTask) {

        ScheduledExecutorFactoryBean scheduledExecutorFactoryBean = new ScheduledExecutorFactoryBean();
        scheduledExecutorFactoryBean.setScheduledExecutorTasks(scheduledExecutorTask);
        return scheduledExecutorFactoryBean;
    }

    @Bean
    public ScheduledExecutorTask scheduledExecutorTask(Runnable runnable) {

        ScheduledExecutorTask scheduledExecutorTask = new ScheduledExecutorTask();
        scheduledExecutorTask.setPeriod(1000);
        scheduledExecutorTask.setRunnable(runnable);
        return scheduledExecutorTask;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(50);
        taskExecutor.setMaxPoolSize(100);
        taskExecutor.setAllowCoreThreadTimeOut(true);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return taskExecutor;
    }
}
