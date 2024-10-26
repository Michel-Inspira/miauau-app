package com.miauau.platform.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdoptionProducer {

    private final KafkaTemplate<String, AdoptionRequestConfirmation> kafkaTemplate;

    public void sendAdoptionRequestConfirmation(
            AdoptionRequestConfirmation adoptionRequestConfirmation) {
        log.info("Sending adoption confirmation request: ");
        Message<AdoptionRequestConfirmation> message =
                MessageBuilder
                        .withPayload(adoptionRequestConfirmation)
                        .setHeader(KafkaHeaders.TOPIC, "adoption-topic")
                        .build();
        kafkaTemplate.send(message);
    }
}
