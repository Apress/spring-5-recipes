package com.apress.springrecipes.nosql.config;

import com.apress.springrecipes.nosql.MongoDBVehicleRepository;
import com.apress.springrecipes.nosql.VehicleRepository;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

@Configuration
public class MongoConfiguration {

    public static final String DB_NAME = "vehicledb";

    @Bean
    public MongoClient mongo() throws UnknownHostException {
        return new MongoClient();
    }

    @Bean
    public VehicleRepository vehicleRepository(MongoClient mongo) {
        return new MongoDBVehicleRepository(mongo, DB_NAME, " vehicles");
    }

}
