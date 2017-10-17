package com.apress.springrecipes.nosql;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories
@ComponentScan
public class StarwarsConfig {

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        return new org.neo4j.ogm.config.Configuration.Builder().uri("bolt://localhost").build();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(configuration(),"com.apress.springrecipes.nosql");
    }

    @Bean
    public Neo4jTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new Neo4jTransactionManager(sessionFactory);
    }

}
