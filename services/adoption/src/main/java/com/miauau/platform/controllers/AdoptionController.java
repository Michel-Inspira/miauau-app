package com.miauau.platform.controllers;

import com.miauau.platform.dto.adoption.AdoptionCandidateResponse;
import com.miauau.platform.dto.adoption.DetailedAdoptionCandidateResponse;
import com.miauau.platform.models.CandidateForm;
import com.miauau.platform.requests.AdoptionFormRequest;
import com.miauau.platform.services.AdoptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/adoptions")
@RequiredArgsConstructor
public class AdoptionController {

    private final AdoptionService service;

    @PostMapping
    public ResponseEntity<CandidateForm> createAdoptionForm(
            @RequestBody @Valid AdoptionFormRequest request
    ) {
        CandidateForm response = service.createAdoptionForm(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/specific-animal/{animalId}")
    public ResponseEntity<List<AdoptionCandidateResponse>> getAdoptionCandidatesByAnimalId(@PathVariable String animalId) {
        List<AdoptionCandidateResponse> response = service.getAdoptionCandidatesByAnimalId(animalId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<DetailedAdoptionCandidateResponse> getById(@PathVariable String candidateId) {
        DetailedAdoptionCandidateResponse response = service.getById(candidateId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
