package com.iuh.health_services.Kafka;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import com.iuh.health_services.Dtos.HealthDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HealthProducer {

    @Autowired
    private KafkaTemplate<String, HealthDto> kafkaTemplate;
    public void sendHealthSuggest(HealthDto healthDto) {
        log.info("Sending health suggest: {}", healthDto);
        Message<HealthDto> messages = MessageBuilder.withPayload(healthDto)
                .setHeader(KafkaHeaders.TOPIC, "health-topic")
                .build();
        kafkaTemplate.send(messages);
    }
}
