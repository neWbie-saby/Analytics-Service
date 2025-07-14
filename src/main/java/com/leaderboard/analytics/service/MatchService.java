package com.leaderboard.analytics.service;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leaderboard.analytics.dto.MatchUserDto;
import com.leaderboard.analytics.entity.MatchUser;
import com.leaderboard.analytics.mapper.MatchUserMapper;
import com.leaderboard.analytics.repository.MatchUserRepository;

@Service
@Transactional(readOnly = true)
public class MatchService {

    private final MatchUserRepository matchUserRepository;

    // @Autowired
    public MatchService(MatchUserRepository matchUserRepository){
        this.matchUserRepository = matchUserRepository;
    }

    public List<MatchUserDto> getAllScoresForMatch(Long matchId){
        
        List<MatchUser> matchUsers = matchUserRepository.findByIdMatchId(matchId);
        return MatchUserMapper.toMatchUserDtoList(matchUsers);
    }

    public List<MatchUserDto> getAllScoresForMatchWithUserDetails(Long matchId){

        List<MatchUser> matchUsers = matchUserRepository.findByMatchIdWithUserDetails(matchId);
        return MatchUserMapper.toDtoListWithUser(matchUsers);
    }

    public MatchUserDto getUserScoreInMatch(Long matchId, Long userId){
        
        MatchUser matchUser = matchUserRepository.findByMatchIdAndUserId(matchId, userId);
        return MatchUserMapper.toMatchUserDto(matchUser);
    }

    public MatchStats getMatchStats(Long matchId){
        List<MatchUser> matchUsers = matchUserRepository.findByIdMatchId(matchId);

        if(matchUsers.isEmpty()) return new MatchStats(matchId, 0, 0.0, 0);

        int totalParticipants = matchUsers.size();
        int highestScore = matchUsers.stream().mapToInt(MatchUser::getScore).max().orElse(0);
        double averageScore = matchUsers.stream().mapToInt(MatchUser::getScore).average().orElse(0.0);

        return new MatchStats(matchId, totalParticipants, averageScore, highestScore);
    }

    public static class MatchStats{

        private final Long matchId;
        private final int totalParticipants;
        private final double averageScore;
        private final int highestScore;
        
        public MatchStats(Long matchId, int totalParticipants, double averageScore, int highestScore) {
            this.matchId = matchId;
            this.totalParticipants = totalParticipants;
            this.averageScore = averageScore;
            this.highestScore = highestScore;
        }

        public Long getMatchId() { return matchId; }
        public int getTotalParticipants() { return totalParticipants; }
        public double getAverageScore() { return averageScore; }
        public int getHighestScore() { return highestScore; }

    }
}
