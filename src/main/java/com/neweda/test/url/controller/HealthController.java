package com.neweda.test.url.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {

    @GetMapping("health")
    public String getVersion() {
        return "1.0";
    }

    @GetMapping
    public String getApplicationStatus() {
        return "Application is up and running";
    }
}
