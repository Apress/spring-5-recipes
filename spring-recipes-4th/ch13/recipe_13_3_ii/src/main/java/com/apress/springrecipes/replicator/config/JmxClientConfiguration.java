package com.apress.springrecipes.replicator.config;

import java.net.MalformedURLException;

import javax.management.MBeanServerConnection;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.access.MBeanProxyFactoryBean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import com.apress.springrecipes.replicator.FileReplicator;

/**
 * Created by marten on 26-05-14.
 */
@Configuration
public class JmxClientConfiguration {

    @Bean
    public FactoryBean<MBeanServerConnection> mbeanServerConnection() throws MalformedURLException {
        MBeanServerConnectionFactoryBean mBeanServerConnectionFactoryBean = new MBeanServerConnectionFactoryBean();
        mBeanServerConnectionFactoryBean.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/replicator");
        return mBeanServerConnectionFactoryBean;
    }

    @Bean
    public MBeanProxyFactoryBean fileReplicatorProxy() throws Exception {
        MBeanProxyFactoryBean fileReplicatorProxy = new MBeanProxyFactoryBean();
        fileReplicatorProxy.setServer(mbeanServerConnection().getObject());
        fileReplicatorProxy.setObjectName("bean:name=documentReplicator");
        fileReplicatorProxy.setProxyInterface(FileReplicator.class);
        return fileReplicatorProxy;
    }
}
