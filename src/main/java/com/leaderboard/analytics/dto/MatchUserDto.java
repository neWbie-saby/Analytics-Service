package com.leaderboard.analytics.dto;

public class MatchUserDto {

    private Integer matchId;
    private Integer userId;
    private Integer score;
    private MatchDto match;
    private UserDto user;

    //Constructors
    public MatchUserDto() {}

    public MatchUserDto(Integer matchId, Integer userId, Integer score, MatchDto match, UserDto user) {
        this.matchId = matchId;
        this.userId = userId;
        this.score = score;
        this.match = match;
        this.user = user;
    }

    //Getters and Setters
    public Integer getMatchId() { return matchId; }
    public void setMatchId(Integer matchId) { this.matchId = matchId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public MatchDto getMatch() { return match; }
    public void setMatch(MatchDto match) { this.match = match; }

    public UserDto getUser() { return user; }
    public void setUser(UserDto user) { this.user = user; }
    
}
