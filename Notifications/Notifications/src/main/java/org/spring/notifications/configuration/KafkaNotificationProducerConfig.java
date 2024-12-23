package org.spring.notifications.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaNotificationProducerConfig {
    @Bean
    public NewTopic notificationTopic() {
        return new NewTopic("notifications", 5, (short) 1);
    }
}
