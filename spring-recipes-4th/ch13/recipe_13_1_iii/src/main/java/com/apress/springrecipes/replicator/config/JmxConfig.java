package com.apress.springrecipes.replicator.config;

import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

import javax.management.remote.JMXConnectorServer;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

import com.apress.springrecipes.replicator.FileReplicator;

@Configuration
public class JmxConfig {

    @Autowired
    private FileReplicator fileReplicator;

    @Bean
    public MBeanExporter mbeanExporter() {
        MBeanExporter mbeanExporter = new MBeanExporter();
        mbeanExporter.setBeans(beansToExport());
        return mbeanExporter;
    }

    private Map<String, Object> beansToExport() {
        Map<String, Object> beansToExport = new HashMap<>();
        beansToExport.put("bean:name=documentReplicator", fileReplicator);
        return beansToExport;
    }


    @Bean
    public FactoryBean<Registry> rmiRegistry() {
        return new RmiRegistryFactoryBean();
    }

    @Bean
    @DependsOn("rmiRegistry")
    public FactoryBean<JMXConnectorServer> connectorServer() {
        ConnectorServerFactoryBean connectorServerFactoryBean = new ConnectorServerFactoryBean();
        connectorServerFactoryBean.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/replicator");
        return connectorServerFactoryBean;
    }
}

