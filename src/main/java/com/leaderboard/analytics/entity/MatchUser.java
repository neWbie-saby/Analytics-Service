package com.leaderboard.analytics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "match_users")
public class MatchUser {

    @EmbeddedId
    private MatchUserId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", referencedColumnName = "id", nullable = false)
    @MapsId("matchId")
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @MapsId("userId")
    private User user;

    @Column(nullable = false)
    private Integer score;

    //Constructors
    public MatchUser() {}

    public MatchUser(Match match, User user, Integer score){
        this.match = match;
        this.user = user;
        this.score = score;
        this.id = new MatchUserId(match.getId(), user.getId());
    }

    //Getters and Setters
    public MatchUserId getId() { return id; }
    public void setId(MatchUserId id) { this.id = id; }

    public Match getMatch() { return match; }
    public void setMatch(Match match){
        this.match = match;
        if(match != null && this.id != null){
            this.id.setMatchId(match.getId());
        }
    }

    public User getUser() { return user; }
    public void setUser(User user){
        this.user = user;
        if(user != null && this.id != null){
            this.id.setUserId(user.getId());
        }
    }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    
}
