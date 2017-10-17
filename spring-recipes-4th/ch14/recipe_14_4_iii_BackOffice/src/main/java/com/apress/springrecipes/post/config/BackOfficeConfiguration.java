package com.apress.springrecipes.post.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import com.apress.springrecipes.post.MailListener;
import com.apress.springrecipes.post.MailMessageConverter;

@Configuration
@EnableJms
public class BackOfficeConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
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
    public SimpleJmsListenerContainerFactory jmsListenerContainerFactory() {
        SimpleJmsListenerContainerFactory listenerContainerFactory = new SimpleJmsListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(connectionFactory());
        listenerContainerFactory.setMessageConverter(mailMessageConverter());
        return listenerContainerFactory;
    }

}
