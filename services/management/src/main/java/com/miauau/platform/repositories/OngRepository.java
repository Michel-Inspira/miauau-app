package com.miauau.platform.repositories;

import com.miauau.platform.models.Ong;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OngRepository extends MongoRepository<Ong, String> {
}
