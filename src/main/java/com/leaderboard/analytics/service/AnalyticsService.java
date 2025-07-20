package com.leaderboard.analytics.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import javax.sql.DataSource;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.leaderboard.analytics.config.AppProperties;
import com.leaderboard.analytics.repository.MatchUserRepository;

@Service
public class AnalyticsService {

    private final AppProperties appProperties;
    private final Executor virtualThreadExecutor;

    //PostgreSQL DB Connections
    private final DataSource dataSource;
    private final MatchUserRepository matchUserRepository;

    // @Autowired
    public AnalyticsService(AppProperties appProperties, 
                            @Qualifier("virtualThreadExecutor") Executor virtualThreadExecutor,
                            DataSource dataSource,
                            MatchUserRepository matchUserRepository){
        this.appProperties = appProperties;
        this.virtualThreadExecutor = virtualThreadExecutor;

        this.dataSource = dataSource;
        this.matchUserRepository = matchUserRepository;
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

    public String testDatabaseConnection(){
        try(Connection connection = dataSource.getConnection()){
            String dbUrl = connection.getMetaData().getURL();
            String dbUser = connection.getMetaData().getUserName();
            long totalMatchUsers = matchUserRepository.count();

            return String.format("Database Connected! URL: %s, User: %s, Total MatchUsers: %d", 
                               dbUrl, dbUser, totalMatchUsers);
        } catch (SQLException e){
            return "Database connection Failed: " + e.getMessage();
        }
    }

    public CompletableFuture<String> processAnalysisAsyncViaGrpc(int matchId){
        return CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(1500);
                String threadInfo = Thread.currentThread().toString();
                return String.format("gRPC-Triggered Analysis completed for match %d (Thread %s)", matchId, threadInfo);
            }
            catch (InterruptedException ex){
                Thread.currentThread().interrupt();
                return "gRPC-Triggered Analysis failed for match - " + matchId;
            }
        }, virtualThreadExecutor);
    }
}
