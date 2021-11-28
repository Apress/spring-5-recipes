package me.suhyuk.spring.boot.rest.dto.kafka;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.admin.TopicListing;

import java.util.Collection;

@Getter
@Setter
public class KafkaTopicList extends KafkaTopic {
    private Collection<TopicListing> listings;
    @Builder
    public KafkaTopicList(Collection<TopicListing> listings) {
        this.listings = listings;
    }
}
