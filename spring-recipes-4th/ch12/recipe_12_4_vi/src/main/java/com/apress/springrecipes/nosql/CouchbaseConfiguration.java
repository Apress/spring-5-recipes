package com.apress.springrecipes.nosql;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

@Configuration
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Override
    protected List<String> getBootstrapHosts() {
        return Collections.singletonList("localhost");
    }

    @Override
    protected String getBucketName() {
        return "default";
    }

    @Override
    protected String getBucketPassword() {
        return "";
    }

    @Bean
    public CouchbaseVehicleRepository vehicleRepository(CouchbaseTemplate couchbaseTemplate) {
        return new CouchbaseVehicleRepository(couchbaseTemplate);
    }
}
