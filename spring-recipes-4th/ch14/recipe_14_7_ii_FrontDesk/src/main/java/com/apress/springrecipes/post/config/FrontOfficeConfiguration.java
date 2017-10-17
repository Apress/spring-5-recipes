package com.apress.springrecipes.post.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.apress.springrecipes.post.FrontDeskImpl;

/**
 * Created by marten on 03-06-14.
 */
@Configuration
@EnableScheduling
public class FrontOfficeConfiguration {


    @Bean
    public KafkaTemplate<Integer, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<Integer, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerFactoryProperties());
    }

    @Bean
    public Map<String, Object> producerFactoryProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return properties;
    }

    @Bean
    public FrontDeskImpl frontDesk() {
        return new FrontDeskImpl(kafkaTemplate());
    }

}
