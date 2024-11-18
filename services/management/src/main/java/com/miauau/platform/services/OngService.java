package com.miauau.platform.services;

import com.miauau.platform.models.Ong;
import com.miauau.platform.repositories.OngRepository;
import com.miauau.platform.requests.OngRequest;
import com.miauau.platform.responses.OngResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OngService {

    private final OngRepository repository;
    private final OngMapper mapper;

    public List<OngResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
    public OngResponse create(OngRequest request) {
        Ong ong = repository.save(mapper.toEntity(request));
        return mapper.toResponse(ong);
    }
}
