package com.miauau.platform.services;

import com.miauau.platform.clients.PersonClient;
import com.miauau.platform.kafka.AdoptionProducer;
import com.miauau.platform.repositories.AdoptionFormRepository;
import com.miauau.platform.requests.AdoptionFormRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdoptionService {

    private final AdoptionFormRepository repository;
    private final PersonClient personClient;

    private final AdoptionProducer adoptionProducer;

    public void createAdoptionForm(AdoptionFormRequest request) {
        // cria person com dados do forms -> OpenFeign
        // Alterar request para estar de acordo com PersonRequest
        // PersonResponse person = personClient.createPerson(request);

        // cria adoption form com dados do forms

        // ?? verificar se existem animais com as condições desejadas
        // envia email confirmando registro de adoção

        // Envia informação para o kafka
//        adoptionProducer.sendAdoptionRequestConfirmation(
//                new AdoptionRequestConfirmation(
//                        request.getMail(),
//                        request.getName())
//        );

    }
}
