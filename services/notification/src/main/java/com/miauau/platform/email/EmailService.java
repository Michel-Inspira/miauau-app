package com.miauau.platform.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static com.miauau.platform.email.EmailTemplates.ADOPTION_REQUEST_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_RELATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendAdoptionConfirmationEmail(String detinationEmail, String adoptantName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(message, MULTIPART_MODE_RELATED, UTF_8.name());
        // TODO: Atualizar email de origem (vaiável de ambiente) email da ong por exemplo
        helper.setFrom("");
        final String templateName = ADOPTION_REQUEST_CONFIRMATION.getTemplate();

        // TODO: Adicionar outras variáveis necesárias ao email
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", adoptantName);

        Context context = new Context();
        context.setVariables(variables);
        helper.setSubject(ADOPTION_REQUEST_CONFIRMATION.getSubject());

        try {
            String html = templateEngine.process(templateName, context);
            helper.setText(html, true);
            helper.setTo(detinationEmail);
            mailSender.send(message);
            // TODO: Ajustar mensagem para variáveis
            log.info(String.format("Email de confirmação de adoção enviado para %s", detinationEmail));
        } catch (MessagingException e) {
            throw new MessagingException("Erro ao enviar email de confirmação de adoção", e);
        }
    }
}
