package org.spring.orders.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.dtolib.order.dto.PubOrder;
import org.spring.orders.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProducerController {
    private final OrderProducer orderProducer;

    @PostMapping("/sent")
    public String sendOrder(@RequestBody PubOrder pubOrder) {
        orderProducer.sendOrder(pubOrder);
        log.info("В контроллере ProducerController отработал метод @PostMapping sendOrder()");

        return "Sent";
    }
}
