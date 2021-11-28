package me.suhyuk.spring.boot.rest.configurations.kafka;

import lombok.Getter;
import lombok.Setter;
import me.suhyuk.spring.boot.rest.utils.Pair;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@EnableConfigurationProperties
@Getter
@Setter
public class KafkaConfiguration {

    private List<KafkaCluster> clusters;

    @Getter
    @Setter
    public static class KafkaCluster {
        private String clusterName;
        private String clusterId;
        private List<String> bootstrapServers;
    }

    @Bean
    public Map<String, Pair<String, AdminClient>> registerAdminClients(KafkaConfiguration kafkaConfiguration) {
        Map<String, Pair<String, AdminClient>> adminClients = new HashMap<>();
        for (KafkaCluster kafkaCluster : kafkaConfiguration.getClusters()) {
            String bootstrapServers = kafkaCluster.getBootstrapServers().stream().collect(Collectors.joining(","));
            Properties props = new Properties();
            props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            adminClients.put(kafkaCluster.getClusterName(), new Pair(kafkaCluster.getClusterId(), AdminClient.create(props)));
        }
        return adminClients;
    }

}
