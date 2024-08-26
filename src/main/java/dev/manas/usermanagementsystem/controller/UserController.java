package dev.manas.usermanagementsystem.controller;

import dev.manas.usermanagementsystem.dto.LoginRequestDto;
import dev.manas.usermanagementsystem.dto.SignUpRequestDto;
import dev.manas.usermanagementsystem.dto.UserResponseDto;
import dev.manas.usermanagementsystem.service.UserService;
import dev.manas.usermanagementsystem.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto>signUp(@RequestBody SignUpRequestDto signUpRequestDto)
    {
        return ResponseEntity.ok(userService.signUp(signUpRequestDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto>getUserByID(@PathVariable long userId)
    {
        return ResponseEntity.ok(userService.findUserByID(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers()
    {
        return ResponseEntity.ok(userService.findALlUser());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean>deleteUserById(@PathVariable long userId)
    {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }

    @DeleteMapping
    public ResponseEntity<Boolean>deleteAllUsers()
    {
        return ResponseEntity.ok(userService.deleteAllUsers());
    }


}
