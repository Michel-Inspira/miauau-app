package com.miauau.platform.controllers;

import com.miauau.platform.services.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/animals/files")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService service;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("id") UUID id, @RequestParam("file") MultipartFile file) {
        service.uploadFile(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateFile(@RequestParam("id") UUID id, @RequestParam("file") MultipartFile file) {
        service.updateFile(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFile(@RequestParam("id") UUID id) {
        service.deleteFile(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
