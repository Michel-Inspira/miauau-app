package com.miauau.platform.services;

import com.miauau.platform.config.FileStorageProperties;
import com.miauau.platform.exceptions.AnimalNotFoundException;
import com.miauau.platform.models.Animal;
import com.miauau.platform.repositories.AnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

import static java.lang.String.format;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;
    private final AnimalRepository animalRepository;


    public FileStorageService(FileStorageProperties fileStorageProperties, AnimalRepository animalRepository) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        this.animalRepository = animalRepository;
    }

    public void uploadFile(UUID id, MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects
                .requireNonNull(file.getOriginalFilename()));
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(
                        format("Animal with id %s not found", id))
                );
        try {
            Path targetLocation = fileStorageLocation.resolve(fileName);
            file.transferTo(targetLocation);

            animal.setImagePath(targetLocation.toString());
            animalRepository.save(animal);
        } catch (IOException ex) {
            throw new RuntimeException(
                    String.format("Could not store file %s", fileName), ex);
        }
    }

    public void updateFile(UUID id, MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects
                .requireNonNull(file.getOriginalFilename()));
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(
                        format("Animal with id %s not found", id))
                );

        try {
            Path targetLocation = fileStorageLocation.resolve(fileName);
            file.transferTo(targetLocation);

            Path oldFilePath = Paths.get(animal.getImagePath());
            if (Files.exists(oldFilePath)) {
                Files.delete(oldFilePath);
            }
            animal.setImagePath(targetLocation.toString());
            animalRepository.save(animal);
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Could not update file %s", fileName), ex);
        }
    }

    public void deleteFile(UUID id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(
                        format("Animal with id %s not found", id))
                );
        try {
            Path oldFilePath = Paths.get(animal.getImagePath());
            if (Files.exists(oldFilePath)) {
                Files.delete(oldFilePath);
            }
            animal.setImagePath(null);
            animalRepository.save(animal);
        } catch (IOException ex) {
            throw new RuntimeException("Could not delete file", ex);
        }
    }
}
