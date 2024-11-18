package com.miauau.platform.clients;

import com.miauau.platform.dto.person.PersonResponse;
import com.miauau.platform.requests.PersonRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "person-service",
        url = "${application.config.person-url}"
)
public interface PersonClient {

    @PostMapping
    PersonResponse createPerson(PersonRequest request);
}
