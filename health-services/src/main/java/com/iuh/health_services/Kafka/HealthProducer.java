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
        kafkaTemplate.send(messages).whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Unable to send message=[{}] due to : {}", healthDto, ex.getMessage());
            } else {
                System.out.println("Partition:" + result.getRecordMetadata().partition());
                log.info("Sent message=[{}] with offset=[{}]", healthDto, result.getRecordMetadata().offset());
            }
        });
    }
    //Tự động chạy lại khi có lỗi
//    @Scheduled(fixedDelay = 10000)
//    public void resendProducer()
//    {
        // Hnah2 đoộng muốn làm
//    }




}
