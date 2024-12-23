package org.spring.payment.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaPayedProducerConfig {
    @Bean
    public NewTopic payedTopic() {
        return new NewTopic("payed_orders", 5, (short) 1);
    }
}
