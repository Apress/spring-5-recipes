package me.suhyuk.spring.boot.rest.configurations.kafka;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

//        @Builder
//        public KafkaCluster(String clusterName, String clusterId, List<String> bootstrapServers) {
//            this.clusterName = clusterName;
//            this.clusterId = clusterId;
//            this.bootstrapServers = bootstrapServers;
//        }
    }
}
