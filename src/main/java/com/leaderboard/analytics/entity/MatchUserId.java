package com.leaderboard.analytics.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class MatchUserId implements Serializable {

    private Integer matchId;
    private Integer userId;

    //Constructors
    public MatchUserId() {}

    public MatchUserId(Integer matchId, Integer userId){
        this.matchId = matchId;
        this.userId = userId;
    }

    public Integer getMatchId() { return matchId; }
    public void setMatchId(Integer matchId) { this.matchId = matchId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    //Override equals() and hashCode() for composite keys
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        MatchUserId that = (MatchUserId) o;
        return Objects.equals(matchId, that.matchId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(userId, matchId);
    }
}
