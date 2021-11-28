package me.suhyuk.spring.boot.rest.dto.kafka;

import lombok.Builder;
import lombok.Getter;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.common.config.ConfigResource;

import java.util.Map;

@Getter
public class KafkaTopicConfigs extends KafkaTopic {
    private Map<ConfigResource, Config> resources;
    @Builder
    public KafkaTopicConfigs(Map<ConfigResource, Config> resources) {
        this.resources = resources;
    }
}
