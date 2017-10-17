package com.apress.springrecipes.course.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.PostgreSQL95Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.apress.springrecipes.course.Course;
import com.apress.springrecipes.course.CourseDao;
import com.apress.springrecipes.course.hibernate.HibernateCourseDao;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class CourseConfiguration {

    @Bean
    public CourseDao courseDao(SessionFactory sessionFactory) {
        return new HibernateCourseDao(sessionFactory);
    }

    @Bean
    public DataSource dataSource() {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/course");
        dataSource.setMinimumIdle(2);
        dataSource.setMaximumPoolSize(5);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.setAnnotatedClasses(Course.class);
        return sessionFactoryBean;
    }

    private Properties hibernateProperties() {

        Properties properties = new Properties();
        properties.setProperty(AvailableSettings.DIALECT, PostgreSQL95Dialect.class.getName());
        properties.setProperty(AvailableSettings.SHOW_SQL, String.valueOf(true));
        properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
        return properties;
    }


}
