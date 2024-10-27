package com.miauau.platform.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Validated
public class Finance {

    @Id
    private String id;
    private Map<String, Object> financeStatus;
}
