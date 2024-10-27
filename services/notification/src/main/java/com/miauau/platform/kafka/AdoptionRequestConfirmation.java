package com.miauau.platform.kafka;

public record AdoptionRequestConfirmation(
        String mail,
        String name
        // TODO: Verificar informações necessárias para envio de email
) {
}
