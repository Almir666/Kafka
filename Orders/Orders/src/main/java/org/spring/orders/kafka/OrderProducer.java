package org.spring.orders.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.dtolib.order.dto.PubOrder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, PubOrder> kafkaTemplate;

    public void sendOrder(PubOrder pubOrder) {
        CompletableFuture<SendResult<String, PubOrder>> future = kafkaTemplate.send("new_orders1", pubOrder);
        future.whenComplete((result, exception)-> {
            if(exception != null) {
                log.error("Failed to send message: {}", exception.getMessage());
            } else {
                log.info("Message sent successfully {}", result.getRecordMetadata());
            }
        });
        log.info("Отработал метод sendOrder() в OrderProducer, сообщение отправлено в топик: new_orders1");
    }
}
