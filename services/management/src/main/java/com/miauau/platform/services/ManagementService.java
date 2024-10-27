package com.miauau.platform.services;

import com.miauau.platform.repositories.FinanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManagementService {

    private final FinanceRepository repository;
    private final ManagementMapper mapper;

    // TODO: implementar métodos de serviço
}
