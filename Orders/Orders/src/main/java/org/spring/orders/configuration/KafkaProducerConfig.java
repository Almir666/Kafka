package org.spring.orders.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic orderTopic() {
        return new NewTopic("new_orders1", 5, (short) 1);
    }
}