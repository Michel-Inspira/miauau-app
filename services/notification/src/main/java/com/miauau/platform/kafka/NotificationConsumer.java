package com.miauau.platform.kafka;

import com.miauau.platform.email.EmailService;
import com.miauau.platform.notification.Notification;
import com.miauau.platform.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.miauau.platform.notification.NotificationType.ADOPTION_FORM_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "adoption-topic")
    public void AdoptionFormConfirmation(AdoptionRequestConfirmation confirmation) throws MessagingException {
        log.info(String.format("Received adoption confirmation request: %s", confirmation));
        repository.save(
                Notification.builder()
                        .type(ADOPTION_FORM_CONFIRMATION)
                        .email(confirmation.mail())
                        .notificationDate(LocalDateTime.now())
                        .build()
        );

        emailService.sendAdoptionConfirmationEmail(
                confirmation.mail(),
                confirmation.name()
        );
    }
}
