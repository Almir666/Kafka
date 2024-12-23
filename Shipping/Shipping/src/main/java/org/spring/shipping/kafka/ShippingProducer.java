package org.spring.shipping.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.dtolib.order.dto.ShippingOrder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippingProducer {
    private final KafkaTemplate<String, ShippingOrder> kafkaTemplate;

    public void sendOrder(ShippingOrder shippingOrder) {
        CompletableFuture<SendResult<String, ShippingOrder>> future = kafkaTemplate.send("shipping_orders", shippingOrder);
        future.whenComplete((result, exception)-> {
            if(exception != null) {
                log.error("Failed to send message: {}", exception.getMessage());
            } else {
                log.info("Message sent successfully {}", result.getRecordMetadata());
            }
        });
        log.info("Отработал метод sendOrder() в ShippingProducer, сообщение отправлено в топик: shipping_orders");
    }
}
