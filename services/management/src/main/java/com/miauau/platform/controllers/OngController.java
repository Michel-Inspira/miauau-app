package com.miauau.platform.controllers;

import com.miauau.platform.requests.OngRequest;
import com.miauau.platform.responses.OngResponse;
import com.miauau.platform.services.OngService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/managements/ongs")
@RequiredArgsConstructor
public class OngController {

    private final OngService ongService;

    @GetMapping
    public ResponseEntity<List<OngResponse>> getAll() {
        return ResponseEntity.ok(ongService.getAll());
    }

    @PostMapping
    public ResponseEntity<OngResponse> create(
            @RequestBody OngRequest request) {
        OngResponse response = ongService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
