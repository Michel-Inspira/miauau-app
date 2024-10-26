package com.miauau.platform.repositories;

import com.miauau.platform.models.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AnimalRepository extends MongoRepository<Animal, UUID> {
}
