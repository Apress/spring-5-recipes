package com.apress.springrecipes.springbatch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.transaction.PlatformTransactionManager;

import com.apress.springrecipes.springbatch.UserRegistration;

/**
 * Created by marten on 17-03-14.
 */
@Configuration
public class UserJob {

    private static final String INSERT_REGISTRATION_QUERY =
            "insert into USER_REGISTRATION(FIRST_NAME, LAST_NAME, COMPANY, ADDRESS,CITY,STATE,ZIP,COUNTY,URL,PHONE_NUMBER,FAX)" +
            " values " +
            "(:firstName,:lastName,:company,:address,:city,:state,:zip,:county,:url,:phoneNumber,:fax)";

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Value("file:${user.home}/batches/registrations.csv")
    private Resource input;



    @Bean
    public Job insertIntoDbFromCsvJob(Step step1) {
        JobBuilder builder = jobs.get("insertIntoDbFromCsvJob");
        return builder
                .start(step1)
                .build();
    }

    @Bean
    protected Step step1(ItemReader<UserRegistration> itemReader) {
                return steps.get("step1")
                .<UserRegistration,UserRegistration>chunk(10)
                    .faultTolerant()
                        .skipLimit(3)
                        .retryLimit(3).retry(DeadlockLoserDataAccessException.class)
                .reader(itemReader)
                .writer(jdbcItemWriter())
                .transactionManager(transactionManager).build();
    }

    @Bean
    @StepScope
    ItemReader<UserRegistration> csvFileReader(@Value("file:${user.home}/batches/registrations.csv") Resource input) {
        FlatFileItemReader<UserRegistration> itemReader = new FlatFileItemReader<>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setResource(input);
        return itemReader;
    }

    @Bean
    ItemWriter<UserRegistration> jdbcItemWriter() {
        JdbcBatchItemWriter<UserRegistration> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql(INSERT_REGISTRATION_QUERY);
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return itemWriter;
    }

    @Bean
    LineMapper<UserRegistration> lineMapper() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("firstName","lastName","company","address","city","state","zip","county","url","phoneNumber","fax");

        BeanWrapperFieldSetMapper<UserRegistration> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(UserRegistration.class);

        DefaultLineMapper<UserRegistration> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
}
