package org.spring.orders.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaProducerConfig {
    @Value("${partitions.number}")
    private int numPartitions;

    @Bean
    public NewTopic orderTopic() {
        return new NewTopic("new_orders1", numPartitions, (short) 1);
    }
}