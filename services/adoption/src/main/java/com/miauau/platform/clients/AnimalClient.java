package com.miauau.platform.clients;

import com.miauau.platform.dto.animal.AnimalResponse;
import com.miauau.platform.dto.person.PersonResponse;
import com.miauau.platform.requests.PersonRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(
        name = "animal-service",
        url = "${application.config.animal-url}"
)
public interface AnimalClient {

    @GetMapping("/{id}")
    AnimalResponse getAnimalById(@PathVariable("id") String id);
}
