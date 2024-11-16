package com.miauau.platform.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {
    private String uploadDir;
}
