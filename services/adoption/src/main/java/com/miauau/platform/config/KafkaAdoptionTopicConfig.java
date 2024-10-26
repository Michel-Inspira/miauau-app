package com.miauau.platform.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaAdoptionTopicConfig {

    @Bean
    public NewTopic adoptionTopic() {
        return TopicBuilder
                .name("adoption-topic")
                .build();
    }
}
