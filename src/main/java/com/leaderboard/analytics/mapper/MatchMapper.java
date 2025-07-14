package com.leaderboard.analytics.mapper;

import com.leaderboard.analytics.dto.MatchDto;
import com.leaderboard.analytics.entity.Match;

public class MatchMapper {

    private MatchMapper() {}

    public static MatchDto toMatchDto(Match match){

        if(match == null) return null;

        MatchDto matchDto = new MatchDto();
        matchDto.setId(match.getId());
        matchDto.setMatchType(match.getMatchType());
        matchDto.setMatchDate(match.getMatchDate());
        matchDto.setCreatedAt(match.getCreatedAt());
        matchDto.setUpdatedAt(match.getUpdatedAt());

        return matchDto;
    }
}
