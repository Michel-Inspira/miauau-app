package com.miauau.platform.models;


import com.miauau.platform.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AnimalListener extends AbstractMongoEventListener<Animal> {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Animal> event) {
        Animal animal = event.getSource();
        if (animal.getId() == null) {
            animal.setId(UUID.randomUUID());
            animal.setAnimalNumber(sequenceGeneratorService.generateSequence(Animal.class.getSimpleName()));
        }
    }
}
