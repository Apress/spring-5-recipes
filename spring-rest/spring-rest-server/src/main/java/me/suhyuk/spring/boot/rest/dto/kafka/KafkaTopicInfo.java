package me.suhyuk.spring.boot.rest.dto.kafka;

import lombok.Builder;
import lombok.Getter;
import org.apache.kafka.clients.admin.TopicDescription;

@Getter
public class KafkaTopicInfo extends KafkaTopic {
    private TopicDescription description;
    @Builder
    public KafkaTopicInfo(TopicDescription description) {
        this.description = description;
    }
}
