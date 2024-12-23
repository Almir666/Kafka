package org.spring.notifications.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.dtolib.order.dto.Notification;
import org.spring.dtolib.order.dto.ShippingOrder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationProducer notificationProducer;

    @KafkaListener(topics = "shipping_orders", groupId = "order_consumers")
    public void listener(ShippingOrder order) {
        log.info("Получено сообщение из топика: shipping_orders");

        if(!order.isDelived()) {
            System.out.println("Заказ " + order.getOrderName() + " не оплачен. Заказ переходит в статус ожидания");
        } else {
            Notification notification = new Notification();
            notification.setNotification("Ваш заказ " + order.getOrderName() + " доставлен");

            notificationProducer.sendOrder(notification);

            System.out.println("Заказ в пути, отправляем клиенту уведомление о доставке");
        }
    }
}
