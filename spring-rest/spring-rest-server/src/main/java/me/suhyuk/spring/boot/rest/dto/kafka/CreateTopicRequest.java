package me.suhyuk.spring.boot.rest.dto.kafka;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class CreateTopicRequest {
    private String topicName;
    private int numPartitions;
    private short replicationFactor;
    @Builder
    public CreateTopicRequest(String topicName, int numPartitions, short replicationFactor) {
        this.topicName = topicName;
        this.numPartitions = numPartitions;
        this.replicationFactor = replicationFactor;
    }
}
