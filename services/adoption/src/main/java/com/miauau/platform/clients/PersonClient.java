package com.miauau.platform.clients;

import com.miauau.platform.dto.person.PersonResponse;
import com.miauau.platform.requests.PersonRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(
        name = "person-service",
        url = "${application.config.person-url}"
)
public interface PersonClient {

    @PostMapping
    PersonResponse createPerson(PersonRequest request);

    @GetMapping("/{id}")
    PersonResponse getPersonById(@PathVariable("id") UUID id);
}
