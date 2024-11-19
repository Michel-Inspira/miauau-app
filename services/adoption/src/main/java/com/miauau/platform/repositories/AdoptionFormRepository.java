package com.miauau.platform.repositories;

import com.miauau.platform.models.CandidateForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AdoptionFormRepository extends MongoRepository<CandidateForm, UUID> {
    @Query("{ 'animalsOfInterest.specificAnimal': ?0 }")
    List<CandidateForm> findBySpecificAnimal(String specificAnimal);
}
