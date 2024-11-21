package com.miauau.platform.repositories;

import com.miauau.platform.models.CandidateForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AdoptionFormRepository extends MongoRepository<CandidateForm, String> {
    @Query("{ 'animalsOfInterest.specificAnimal': ?0 }")
    List<CandidateForm> findBySpecificAnimal(String specificAnimal);
}
