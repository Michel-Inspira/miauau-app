package com.miauau.platform.services;

import com.miauau.platform.clients.PersonClient;
import com.miauau.platform.dto.person.PersonResponse;
import com.miauau.platform.kafka.AdoptionProducer;
import com.miauau.platform.models.CandidateForm;
import com.miauau.platform.repositories.AdoptionFormRepository;
import com.miauau.platform.requests.AdoptionFormRequest;
import com.miauau.platform.requests.PersonRequest;
import com.miauau.platform.requests.PersonalInformationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdoptionService {

    private final AdoptionFormRepository repository;
    private final PersonClient personClient;
    private final AdoptionFormMapper mapper;

    private final AdoptionProducer adoptionProducer;

    public CandidateForm createAdoptionForm(AdoptionFormRequest request) {
        PersonResponse person = personClient.createPerson(this.getPersonRequestFromAdoptionForm(request));
        CandidateForm candidateForm = repository.save(mapper.toEntity(request, person));

        // ?? verificar se existem animais com as condições desejadas
        // envia email confirmando registro de adoção

        // Envia informação para o kafka
//        adoptionProducer.sendAdoptionRequestConfirmation(
//                new AdoptionRequestConfirmation(
//                        request.getMail(),
//                        request.getName())
//        );
        return candidateForm;
    }

    private PersonRequest getPersonRequestFromAdoptionForm(AdoptionFormRequest request) {
        PersonalInformationRequest personalInfo = request.personalInformation();
        return new PersonRequest(
                personalInfo.identification().name(),
                false,
                "ADOPTION_CANDIDATE",
                personalInfo.identification().email(),
                personalInfo.identification().phone(),
                personalInfo.address().zipcode(),
                personalInfo.address().street(),
                personalInfo.address().number(),
                personalInfo.address().complement(),
                personalInfo.address().neighborhood(),
                personalInfo.identification().cpf(),
                personalInfo.identification().rg(),
                personalInfo.identification().birthDate(),
                personalInfo.identification().landline()
        );
    }
}
