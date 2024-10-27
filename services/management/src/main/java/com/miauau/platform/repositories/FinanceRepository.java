package com.miauau.platform.repositories;


import com.miauau.platform.models.Finance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FinanceRepository extends MongoRepository<Finance, String> {
}
