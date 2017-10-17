package com.apress.springrecipes.post.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

import com.apress.springrecipes.post.MailListener;

/**
 * Created by marten on 02-06-14.
 */
@Configuration
@EnableJms
public class BackOfficeConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        return new CachingConnectionFactory(connectionFactory());
    }

    @Bean
    public MailListener mailListener() {
        return new MailListener();
    }

    @Bean
    public SimpleJmsListenerContainerFactory jmsListenerContainerFactory() {
        SimpleJmsListenerContainerFactory listenerContainerFactory = new SimpleJmsListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(cachingConnectionFactory());
        return listenerContainerFactory;
    }
}
