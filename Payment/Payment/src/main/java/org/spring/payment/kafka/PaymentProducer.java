package org.spring.payment.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.dtolib.order.dto.PayedOrder;
import org.spring.dtolib.order.dto.PubOrder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentProducer {
    private final KafkaTemplate<String, PayedOrder> kafkaTemplate;

    public void sendOrder(PayedOrder pubOrder) {
        CompletableFuture<SendResult<String, PayedOrder>> future = kafkaTemplate.send("payed_orders", pubOrder);
        future.whenComplete((result, exception)-> {
            if(exception != null) {
                log.error("Failed to send message: {}", exception.getMessage());
            } else {
                log.info("Message sent successfully {}", result.getRecordMetadata());
            }
        });
        log.info("Отработал метод sendOrder() в PaymentProducer, сообщение отправлено в топик: payment_orders");
    }
}