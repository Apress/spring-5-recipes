package com.apress.springrecipes.course.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import com.apress.springrecipes.course.CourseDao;
import com.apress.springrecipes.course.jpa.JpaCourseDao;

@Configuration
public class CourseConfiguration {

    @Bean
    public CourseDao courseDao(EntityManagerFactory entityManagerFactory) {
        return new JpaCourseDao(entityManagerFactory);
    }

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {

        LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
        emf.setPersistenceUnitName("course");
        return emf;
    }
}
