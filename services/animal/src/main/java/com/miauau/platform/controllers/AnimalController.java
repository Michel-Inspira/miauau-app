package com.miauau.platform.controllers;

import com.miauau.platform.requests.AnimalRequest;
import com.miauau.platform.responses.AnimalResponse;
import com.miauau.platform.services.AnimalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService service;

    @GetMapping
    public ResponseEntity<List<AnimalResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> getById(@PathVariable UUID id) {
        AnimalResponse response = service.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnimalResponse> create(
            @RequestBody AnimalRequest request) {
        AnimalResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> update(
            @PathVariable UUID id, @RequestBody @Valid AnimalRequest request) {
        AnimalResponse response = service.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/getNotAdoptedByOng/{ongId}")
    public ResponseEntity<List<AnimalResponse>> getNotAdoptedByOng(@PathVariable String ongId) {
        return ResponseEntity.ok(service.getNotAdoptedByOng(ongId));
    }
}
