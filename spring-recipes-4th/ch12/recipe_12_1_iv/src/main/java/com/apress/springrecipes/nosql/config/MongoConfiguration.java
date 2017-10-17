package com.apress.springrecipes.nosql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.apress.springrecipes.nosql.MongoDBVehicleRepository;
import com.apress.springrecipes.nosql.VehicleRepository;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfiguration {

    public static final String DB_NAME = "vehicledb";

    @Bean
    public MongoTemplate mongo(MongoClient mongo) throws Exception {
        return new MongoTemplate(mongo, DB_NAME);
    }

    @Bean
    public MongoClientFactoryBean mongoClientFactoryBean() {
        return new MongoClientFactoryBean();
    }


    @Bean
    public VehicleRepository vehicleRepository(MongoTemplate mongo) {
        return new MongoDBVehicleRepository(mongo);
    }

}
