package com.miauau.platform.controllers;

import com.miauau.platform.config.FileStorageConfig;
import com.miauau.platform.models.Animal;
import com.miauau.platform.repositories.AnimalRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/animals/blob")
public class FileStorageController {

    @Value("${api.url}")
    private String apiUrl;
    private final Path fileStorageLocation;
    private final AnimalRepository animalRepository;


    public FileStorageController(FileStorageConfig fileStorageConfig, AnimalRepository animalRepository) {
        this.fileStorageLocation = Path.of(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        this.animalRepository = animalRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("id") UUID id, @RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found"));


        try {
            Path targetLocation = fileStorageLocation.resolve(fileName);
            file.transferTo(targetLocation);

            String fileDownloadUri = ServletUriComponentsBuilder.fromHttpUrl(apiUrl)
                    .path("/api/v1/animals/blob/download/")
                    .path(fileName)
                    .toUriString();

            animal.setImagePath(fileDownloadUri);
            animalRepository.save(animal);

            return new ResponseEntity<>(fileDownloadUri, HttpStatus.OK);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        Path filePath = fileStorageLocation.resolve(fileName).normalize();


        try {
            Resource resource = new UrlResource(filePath.toUri());
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
