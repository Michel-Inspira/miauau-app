package com.miauau.platform.repositories;

import com.miauau.platform.models.CandidateForm;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AdoptionFormRepository extends MongoRepository<CandidateForm, UUID> {
}
