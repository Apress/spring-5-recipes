package com.apress.springrecipes.course.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.PostgreSQL95Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.apress.springrecipes.course.Course;
import com.apress.springrecipes.course.CourseDao;
import com.apress.springrecipes.course.hibernate.HibernateCourseDao;

@Configuration
public class CourseConfiguration {

    @Bean
    public CourseDao courseDao(SessionFactory sessionFactory) {
        return new HibernateCourseDao(sessionFactory);
    }


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.setAnnotatedClasses(Course.class);
        return sessionFactoryBean;
    }

    private Properties hibernateProperties() {

        Properties properties = new Properties();
        properties.setProperty(AvailableSettings.URL, "jdbc:postgresql://localhost:5432/course");
        properties.setProperty(AvailableSettings.USER, "postgres");
        properties.setProperty(AvailableSettings.PASS, "password");
        properties.setProperty(AvailableSettings.DIALECT, PostgreSQL95Dialect.class.getName());
        properties.setProperty(AvailableSettings.SHOW_SQL, String.valueOf(true));
        properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
        return properties;
    }
}
