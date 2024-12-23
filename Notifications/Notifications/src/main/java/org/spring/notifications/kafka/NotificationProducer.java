package org.spring.notifications.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.dtolib.order.dto.Notification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationProducer {
    private final KafkaTemplate<String, Notification> kafkaTemplate;

    public void sendOrder(Notification notification) {
        CompletableFuture<SendResult<String, Notification>> future = kafkaTemplate.send("notifications", notification);
        future.whenComplete((result, exception)-> {
            if(exception != null) {
                log.error("Failed to send message: {}", exception.getMessage());
            } else {
                log.info("Message sent successfully {}", result.getRecordMetadata());
            }
        });
        log.info("Отработал метод sendOrder() в NotificationProducer, сообщение отправлено в топик: notifications");
    }
}
