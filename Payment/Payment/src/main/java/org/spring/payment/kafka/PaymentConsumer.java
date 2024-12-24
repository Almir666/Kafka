package org.spring.payment.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.dtolib.order.dto.Notification;
import org.spring.dtolib.order.dto.PayedOrder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.spring.dtolib.order.dto.PubOrder;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentConsumer {
    private final PaymentProducer paymentProducer;
    private final NotificationProducer notificationProducer;

    @KafkaListener(topics = "new_orders1", groupId = "order_consumers")
    public void listener(PubOrder order, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        log.info("Получено сообщение из топика: new_orders1");

        if(!order.isPayment()) {
            System.out.println("Оплата не поступила, заказ переходит в режим ожидания");

            Notification notification = new Notification();
            notification.setNotification("Ваш заказ в режиме ожидания, ждем оплату");

            notificationProducer.sendOrder(notification);
        } else {
            PayedOrder payedOrder = new PayedOrder();
            payedOrder.setOrderName(order.getOrderName());
            payedOrder.setPayed(true);

            paymentProducer.sendOrder(payedOrder);

            System.out.println("Заказ успешно обработан и готовится к отправке");
        }
    }
}
