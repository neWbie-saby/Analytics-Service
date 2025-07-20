package com.leaderboard.analytics;


import java.util.concurrent.CompletableFuture;

// import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.leaderboard.analytics.service.AnalyticsService;


@RestController
public class HelloController {
    
    private final AnalyticsService analyticsService;

    // @Autowired
    public HelloController(AnalyticsService analyticsService){
        this.analyticsService = analyticsService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello from Spring Boot Analytics Service";
    }

    @GetMapping("/health")
    public String health() {
        return "Service is running";
    }
    
    @GetMapping("/info")
    public String getServiceInfo() {
        return analyticsService.getServiceInfo();
    }
    
    @GetMapping("/analyze/{matchId}")
    public CompletableFuture<String> analyze(@PathVariable int matchId) {
        return analyticsService.processAnalysisAsync(matchId);
    }    
    
    @GetMapping("/db-test")
    public String testDatabase(){
        return analyticsService.testDatabaseConnection();
    }
    
    @GetMapping("/grpc-info")
    public String grpcInfo(){
        return """
                gRPC Server Information:
                - Server PORT: 9090
                - Service: analytics.AnalyticsService
                - Method: TriggerMatchAnalysis
                - Health Check: Available
                """;
    }
}
