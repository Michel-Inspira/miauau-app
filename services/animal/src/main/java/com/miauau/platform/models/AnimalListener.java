package com.miauau.platform.models;


import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AnimalListener extends AbstractMongoEventListener<Animal> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Animal> event) {
        Animal animal = event.getSource();
        if (animal.getId() == null) {
            animal.setId(UUID.randomUUID());
        }
    }
}
