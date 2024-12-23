package org.spring.shipping.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.dtolib.order.dto.PayedOrder;
import org.spring.dtolib.order.dto.ShippingOrder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippingConsumer {
    private final ShippingProducer shippingProducer;

    @KafkaListener(topics = "payed_orders", groupId = "order_consumers")
    public void listener(PayedOrder order) {
        log.info("Получено сообщение из топика: payed_orders");

        System.out.println("Обрабатывается заказ: " + order.toString());

        if(order.isPayed()) {
            ShippingOrder shippingOrder = new ShippingOrder();
            shippingOrder.setOrderName(order.getOrderName());
            shippingOrder.setDelived(true);

            shippingProducer.sendOrder(shippingOrder);

            System.out.println("Реализуем доставку");
        }
    }
}
