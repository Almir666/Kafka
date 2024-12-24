package org.spring.payment.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaPayedProducerConfig {
    @Value("${partitions.number}")
    private int numPartitions;
    @Bean
    public NewTopic payedTopic() {
        return new NewTopic("payed_orders", numPartitions, (short) 1);
    }
}
