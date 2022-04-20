package me.kyeongho.userservice.service;

import me.kyeongho.userservice.dto.UserDto;
import me.kyeongho.userservice.entity.UserEntity;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
}
