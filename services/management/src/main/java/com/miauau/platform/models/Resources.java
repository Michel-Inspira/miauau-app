package com.miauau.platform.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Validated
public class Resources {

    @Id
    private String id = UUID.randomUUID().toString();
    private String ongId;
    private Map<String, Object> resourcesStatus;
}
