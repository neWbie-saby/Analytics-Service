package com.leaderboard.analytics.mapper;

import com.leaderboard.analytics.dto.UserDto;
import com.leaderboard.analytics.entity.User;

public class UserMapper {

    private UserMapper() {}

    public static UserDto toUserDto(User user){
        
        if(user == null) return null;

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());;
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        return userDto;
    }
}
