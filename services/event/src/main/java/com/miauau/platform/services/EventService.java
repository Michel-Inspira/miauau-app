package com.miauau.platform.services;

import com.miauau.platform.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final EventMapper mapper;
}
