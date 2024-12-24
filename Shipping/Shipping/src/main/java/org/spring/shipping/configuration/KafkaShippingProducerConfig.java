package org.spring.shipping.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaShippingProducerConfig {
    @Value("${partitions.number}")
    private int numPartitions;
    @Bean
    public NewTopic shippingTopic() {
        return new NewTopic("shipping_orders", numPartitions, (short) 1);
    }
}
