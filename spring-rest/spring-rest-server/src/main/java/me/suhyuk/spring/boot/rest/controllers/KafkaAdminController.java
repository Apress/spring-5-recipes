package me.suhyuk.spring.boot.rest.controllers;

import lombok.AllArgsConstructor;
import me.suhyuk.spring.boot.rest.configurations.kafka.KafkaConfiguration;
import me.suhyuk.spring.boot.rest.dto.kafka.MyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/kafka/clusters")
public class KafkaAdminController {

    private KafkaConfiguration kafkaConfiguration;

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

    @GetMapping("/{clusterName}/topics")
    @ResponseBody
    public void getTopics(@PathVariable String clusterName) {
    }

}
