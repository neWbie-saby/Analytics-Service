package com.leaderboard.analytics.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.leaderboard.analytics.config.AppProperties;

@Service
public class AnalyticsService {

    private final AppProperties appProperties;
    private final Executor virtualThreadExecutor;

    // @Autowired
    public AnalyticsService(AppProperties appProperties, @Qualifier("virtualThreadExecutor") Executor virtualThreadExecutor){
        this.appProperties = appProperties;
        this.virtualThreadExecutor = virtualThreadExecutor;
    }

    public String getServiceInfo() {
        return appProperties.toString();
    }

    public CompletableFuture<String> processAnalysisAsync(int matchId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return "Analysis completed for match - " + matchId + " (processed by " + Thread.currentThread() + ")";
            }
            catch (InterruptedException e){
                return "Analysis failed for match -" + matchId;
            }
        }, virtualThreadExecutor);
    }
}
