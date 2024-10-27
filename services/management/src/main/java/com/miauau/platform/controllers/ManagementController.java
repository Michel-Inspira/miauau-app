package com.miauau.platform.controllers;

import com.miauau.platform.services.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/finances")
@RequiredArgsConstructor
public class ManagementController {

    private final ManagementService service;

    // TODO: implementar endpoints
}
