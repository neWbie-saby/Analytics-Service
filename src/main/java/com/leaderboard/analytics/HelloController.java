package com.leaderboard.analytics;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloController {
    
    @GetMapping("/")
    public String hello() {
        return "Hello from Spring Boot Analytics Service";
    }

    @GetMapping("/health")
    public String health() {
        return "Service is running";
    }
    
    
}
