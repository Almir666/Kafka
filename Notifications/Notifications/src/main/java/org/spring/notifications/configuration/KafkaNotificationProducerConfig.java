package org.spring.notifications.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaNotificationProducerConfig {
    @Value("${partitions.number}")
    private int numPartitions;
    @Bean
    public NewTopic notificationTopic() {
        return new NewTopic("notifications", numPartitions, (short) 1);
    }
}
