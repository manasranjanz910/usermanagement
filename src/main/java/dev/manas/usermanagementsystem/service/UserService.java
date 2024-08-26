package dev.manas.usermanagementsystem.service;

import dev.manas.usermanagementsystem.dto.LoginRequestDto;
import dev.manas.usermanagementsystem.dto.SignUpRequestDto;
import dev.manas.usermanagementsystem.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto signUp(SignUpRequestDto signupRequestDto);
    UserResponseDto login(LoginRequestDto loginRequestDto);

    UserResponseDto findUserByID(long userId);

    List<UserResponseDto> findALlUser();

    boolean deleteUserById(long userId);

    boolean deleteAllUsers();
}

