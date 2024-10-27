package com.miauau.platform.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Ong {

    @Id
    private String id;
    private String name;
    private String occupation;
    private String email;
    private Finance finance;
    private Resources resources;
}
