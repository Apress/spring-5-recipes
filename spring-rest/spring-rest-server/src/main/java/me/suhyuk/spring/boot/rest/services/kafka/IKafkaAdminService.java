package me.suhyuk.spring.boot.rest.services.kafka;

import me.suhyuk.spring.boot.rest.dto.kafka.KafkaTopicConfigs;
import me.suhyuk.spring.boot.rest.dto.kafka.KafkaTopicInfo;
import me.suhyuk.spring.boot.rest.dto.kafka.KafkaTopicList;
import java.util.concurrent.ExecutionException;

public interface IKafkaAdminService {

    // 토픽 기본 목록 (N개) - KafkaTopicList - TopicListing
    public KafkaTopicList listTopics(String clusterName) throws ExecutionException, InterruptedException;
    // 토픽 정보 (1개) - KafkaTopicInfo <- TopicDescription
    public KafkaTopicInfo getTopicInfo(String clusterName, String topicName) throws ExecutionException, InterruptedException;
    // 토픽 상세 설정 정보 (1개) - KafkaTopicConfig
    public KafkaTopicConfigs getTopicConfigs(String clusterName, String topicName) throws ExecutionException, InterruptedException;
    // 포픽 생성 (1개) - createTopic
    public void createTopic(String clusterName, String topicName, int numPartitions, short replicationFactor) throws ExecutionException, InterruptedException;
}
