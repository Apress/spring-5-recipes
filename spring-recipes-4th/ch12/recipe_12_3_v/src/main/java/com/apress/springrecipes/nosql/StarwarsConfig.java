package com.apress.springrecipes.nosql;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class StarwarsConfig {

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory("com.apress.springrecipes.nosql");
    }

    @Bean
    public Neo4jStarwarsRepository starwarsRepository(SessionFactory sessionFactory) {
        return new Neo4jStarwarsRepository(sessionFactory);
    }

    @Bean
    public Neo4jTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new Neo4jTransactionManager(sessionFactory);
    }

}
