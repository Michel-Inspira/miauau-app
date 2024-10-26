package com.miauau.platform.controllers;

import com.miauau.platform.requests.AdoptionFormRequest;
import com.miauau.platform.services.AdoptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/adoptions")
@RequiredArgsConstructor
public class AdoptionController {

    private final AdoptionService service;

    @PostMapping
    public ResponseEntity<?> createAdoptionForm(
            @RequestBody @Valid AdoptionFormRequest request
    ) {
        service.createAdoptionForm(request);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
