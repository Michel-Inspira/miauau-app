package com.miauau.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdoptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdoptionApplication.class, args);
    }

}
