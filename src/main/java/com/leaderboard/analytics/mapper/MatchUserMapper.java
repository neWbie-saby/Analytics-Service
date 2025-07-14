package com.leaderboard.analytics.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.leaderboard.analytics.dto.MatchUserDto;
import com.leaderboard.analytics.entity.MatchUser;

public class MatchUserMapper {

    private MatchUserMapper() {}

    public static MatchUserDto toMatchUserDto(MatchUser matchUser){

        if (matchUser == null) return null;

        MatchUserDto dto = new MatchUserDto();
        dto.setMatchId(matchUser.getId().getMatchId());
        dto.setUserId(matchUser.getId().getUserId());
        dto.setScore(matchUser.getScore());

        return dto;
    }

    public static List<MatchUserDto> toMatchUserDtoList(List<MatchUser> matchUsers){

        if (matchUsers == null) return null;

        return matchUsers.stream()
                         .map(MatchUserMapper::toMatchUserDto)
                         .collect(Collectors.toList());
    }

    public static MatchUserDto toDtoWithUser(MatchUser matchUser){
        
        if (matchUser == null) return null;

        MatchUserDto dto = toMatchUserDto(matchUser);

        dto.setUser(UserMapper.toUserDto(matchUser.getUser()));

        //since only User Details were requested per MatchUser
        // dto.setMatch(MatchMapper.toMatchDto(matchUser.getMatch()));

        return dto;
    }

    public static List<MatchUserDto> toDtoListWithUser(List<MatchUser> matchUsers){

        if (matchUsers == null) return null;

        return matchUsers.stream()
                         .map(MatchUserMapper::toDtoWithUser)
                         .collect(Collectors.toList());
    }
}
