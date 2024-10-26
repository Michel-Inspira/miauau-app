package com.miauau.platform.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class AdoptionCandidate {

    @Id
    private UUID id;
    private String personId;
    private Map<String, String> candidateInfo;
    // TODO: Verificar quais dessas info já estão no serviço de pessoas
}
