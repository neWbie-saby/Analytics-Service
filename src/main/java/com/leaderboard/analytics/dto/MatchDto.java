package com.leaderboard.analytics.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MatchDto {
    
    private Integer id;
    private String matchType;
    private LocalDate matchDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    //Constructors
    public MatchDto() {}

    public MatchDto(Integer id, String matchType, LocalDate matchDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.matchType = matchType;
        this.matchDate = matchDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMatchType() { return matchType; }
    public void setMatchType(String matchType) { this.matchType = matchType; }

    public LocalDate getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDate matchDate) { this.matchDate = matchDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
        
}
