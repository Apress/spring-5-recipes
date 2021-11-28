package me.suhyuk.spring.boot.rest.controllers;

import lombok.AllArgsConstructor;
import me.suhyuk.spring.boot.rest.configurations.kafka.KafkaConfiguration;
import me.suhyuk.spring.boot.rest.dto.kafka.CreateTopicRequest;
import me.suhyuk.spring.boot.rest.dto.kafka.KafkaTopicList;
import me.suhyuk.spring.boot.rest.dto.kafka.MyObject;
import me.suhyuk.spring.boot.rest.services.kafka.KafkaAdminService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/kafka/clusters")
public class KafkaAdminController {

    private KafkaConfiguration kafkaConfiguration;
    private KafkaAdminService kafkaAdminService;

    @GetMapping("/foo")
    public String foo() {
        return "foo";
    }

    @GetMapping("/bar")
    @ResponseBody
    public MyObject getMyObject() {
        return MyObject.builder().name("my-object").value(20211127).build();
    }

    @GetMapping("/clusterName")
    @ResponseBody
    public String getBootstrapServers() {
        KafkaConfiguration.KafkaCluster kafkaCluster = kafkaConfiguration.getClusters().get(0);
        return kafkaCluster.getClusterName();
    }

    @GetMapping("/{clusterName}/string")
    @ResponseBody
    public String getString(@PathVariable String clusterName) throws ExecutionException, InterruptedException {
        KafkaTopicList list = kafkaAdminService.listTopics(clusterName);
        return list.toString();
    }

    @GetMapping(value = "/{clusterName}/object", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public KafkaTopicList getObject(@PathVariable String clusterName) throws ExecutionException, InterruptedException {
        KafkaTopicList list = kafkaAdminService.listTopics(clusterName);
        return list;
    }

    // https://www.baeldung.com/jackson-exception
    @GetMapping("/{clusterName}/topics")
    @ResponseBody
    public ResponseEntity<String> listTopics(@PathVariable String clusterName) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(kafkaAdminService.listTopics(clusterName).toString());
    }

    @GetMapping("/{clusterName}/topics/{topicName}")
    @ResponseBody
    public ResponseEntity<String> getTopicInfo(@PathVariable String clusterName, @PathVariable String topicName) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(kafkaAdminService.getTopicInfo(clusterName, topicName).toString());
    }

    @GetMapping("/{clusterName}/topics/{topicName}/configs")
    @ResponseBody
    public ResponseEntity<String> getTopicConfigs(@PathVariable String clusterName, @PathVariable String topicName) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(kafkaAdminService.getTopicConfigs(clusterName, topicName).toString());
    }

    @PostMapping("/{clusterName}/topics")
    public void createTopic(@PathVariable String clusterName,
                            @RequestBody CreateTopicRequest req) throws ExecutionException, InterruptedException {
        kafkaAdminService.createTopic(clusterName, req.getTopicName(), req.getNumPartitions(), req.getReplicationFactor());
    }

}
