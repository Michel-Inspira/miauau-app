package com.miauau.platform.controllers;

import com.miauau.platform.services.BlobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/animals/blob")
@RequiredArgsConstructor
public class BlobController {

    private final BlobService service;


    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("id") UUID id, @RequestParam("file") MultipartFile file) {
        String fileName = service.upload(id, file);
        return new ResponseEntity<>(fileName, HttpStatus.CREATED);
    }
}
