package com.iuh.health_services.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaHealthService_Topic {
    @Bean
    public NewTopic healthTopic() {
        return TopicBuilder.name("health-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
