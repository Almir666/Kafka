package org.spring.orders.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.spring.dtolib.order.dto.Notification;

@Service
@Slf4j
public class NotificationListener {
    @KafkaListener(topics = "notifications", groupId = "order_consumers")
    public void listener(Notification order) {
        log.info("Получено сообщение из топика: notifications");

        System.out.println(order.toString());
    }
}
