package com.iuh.users_healths.Kafka;

import com.iuh.users_healths.Dtos.HealthDto;
import com.iuh.users_healths.IServices.IUsersHealthServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import static java.lang.String.format;
@Service
@Slf4j
public class HealthConsumer {
    @Autowired
    private IUsersHealthServices usersHealthServices;
        @KafkaListener(topics = "health-topic", groupId = "healthGroup")
        public void consumeHealth(HealthDto healthDto) {
            log.info(format("Consuming the message from health-topic Topic:: %s", healthDto));
            usersHealthServices.saveHealthOfUser(healthDto);
            // Gưi phản hồi đến người dùng

        }
}
