package com.apress.springrecipes.post.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import com.apress.springrecipes.post.MailListener;
import com.apress.springrecipes.post.MailMessageConverter;

/**
 * Created by marten on 02-06-14.
 */
@Configuration
@EnableJms
public class BackOfficeConfiguration {

    @Bean(destroyMethod = "stop")
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactoryToUse = new ActiveMQConnectionFactory("tcp://localhost:61616");
        PooledConnectionFactory connectionFactory = new PooledConnectionFactory();
        connectionFactory.setConnectionFactory(connectionFactoryToUse);
        return connectionFactory;
    }

    @Bean
    public MailListener mailListener() {
        return new MailListener();
    }

    @Bean
    public MailMessageConverter mailMessageConverter() {
        return new MailMessageConverter();
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory listenerContainerFactory = new DefaultJmsListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(connectionFactory());
        listenerContainerFactory.setMessageConverter(mailMessageConverter());
        listenerContainerFactory.setSessionTransacted(true);
        return listenerContainerFactory;
    }


}
