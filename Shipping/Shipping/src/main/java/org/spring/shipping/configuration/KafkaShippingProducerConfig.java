package org.spring.shipping.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaShippingProducerConfig {
    @Bean
    public NewTopic shippingTopic() {
        return new NewTopic("shipping_orders", 5, (short) 1);
    }
}
