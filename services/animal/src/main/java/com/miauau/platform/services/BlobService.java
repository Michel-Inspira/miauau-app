package com.miauau.platform.services;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobStorageException;
import com.miauau.platform.models.Animal;
import com.miauau.platform.repositories.AnimalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class BlobService {

    private final BlobServiceClient blobServiceClient;
    private final AnimalRepository animalRepository;

    @Value("${AZURE_STORAGE_ENDPOINT}")
    private String azureStorageEndpoint;

    public BlobService(BlobServiceClient blobServiceClient, AnimalRepository animalRepository) {
        this.blobServiceClient = blobServiceClient;
        this.animalRepository = animalRepository;
    }


    public String upload(UUID id, MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects
                .requireNonNull(file.getOriginalFilename()));
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found"));

        try {
            BlobClient blobClient =
                    blobServiceClient
                            .getBlobContainerClient("images")
                            .getBlobClient(fileName);
            blobClient.upload(file.getInputStream(), false);

            String blobName = blobClient.getBlobName();
            String imagePath = azureStorageEndpoint + "/images/" + blobName;

            animal.setImagePath(imagePath);
            animalRepository.save(animal);
            return blobName;
        } catch (BlobStorageException | IOException ex) {
            log.error("Error when uploading file", ex);
            throw new RuntimeException("Error during file upload");
        }
    }
}
