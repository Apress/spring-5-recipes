package com.apress.springrecipes.nosql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

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
    public CouchbaseVehicleRepository vehicleRepository(CouchbaseTemplate couchbaseTemplate) {
        return new CouchbaseVehicleRepository(couchbaseTemplate);
    }

    @Bean
    public CouchbaseTemplate couchbaseTemplate(Cluster cluster, Bucket bucket) {
        return new CouchbaseTemplate(cluster.clusterManager("default","").info(), bucket);
    }
}
