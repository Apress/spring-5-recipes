package com.apress.springrecipes.nosql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class CouchbaseConfiguration {

    @Bean(destroyMethod = "disconnect")
    public Cluster cluster() {
        return CouchbaseCluster.create();
    }

    @Bean
    public Bucket bucket(Cluster cluster) {
        return cluster.openBucket();
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    @Bean
    public CouchbaseVehicleRepository vehicleRepository(Bucket bucket, ObjectMapper mapper) {
        return new CouchbaseVehicleRepository(bucket, mapper);
    }
}
