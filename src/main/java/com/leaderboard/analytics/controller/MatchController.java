package com.leaderboard.analytics.controller;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leaderboard.analytics.dto.MatchUserDto;
import com.leaderboard.analytics.service.MatchService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    // @Autowired
    public MatchController(MatchService matchService){
        this.matchService = matchService;
    }

    @GetMapping("/{matchId}/scores")
    public List<MatchUserDto> getMatchScores(@PathVariable Long matchId) {
        return matchService.getAllScoresForMatch(matchId);
    }

    @GetMapping("/{matchId}/scores/detailed")
    public List<MatchUserDto> getMatchScoresDetailed(@PathVariable Long matchId) {
        return matchService.getAllScoresForMatchWithUserDetails(matchId);
    }
    
    @GetMapping("/{matchId}/users/{userId}/score")
    public MatchUserDto getUserScore(@PathVariable Long matchId, @PathVariable Long userId){
        return matchService.getUserScoreInMatch(matchId, userId);
    }

    @GetMapping("/{matchId}/stats")
    public MatchService.MatchStats getMatchStats(@PathVariable Long matchId){
        return matchService.getMatchStats(matchId);
    }
}
