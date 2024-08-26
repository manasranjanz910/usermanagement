package dev.manas.usermanagementsystem.service;

import dev.manas.usermanagementsystem.dto.LoginRequestDto;
import dev.manas.usermanagementsystem.dto.RoleResponseDto;
import dev.manas.usermanagementsystem.dto.SignUpRequestDto;
import dev.manas.usermanagementsystem.dto.UserResponseDto;
import dev.manas.usermanagementsystem.entity.Role;
import dev.manas.usermanagementsystem.entity.User;
import dev.manas.usermanagementsystem.exception.InvalidPasswordException;
import dev.manas.usermanagementsystem.exception.RoleNotFoundException;
import dev.manas.usermanagementsystem.exception.UserNotFoundException;
import dev.manas.usermanagementsystem.repository.RoleRepository;
import dev.manas.usermanagementsystem.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public UserServiceImpl(RoleRepository roleRepository,UserRepository userRepository)
    {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
    @Override
    public UserResponseDto signUp(SignUpRequestDto signupRequestDto) {
        logger.info("User SignUp Successful...........");
        Role role = roleRepository.findById(signupRequestDto.getRoleId()).orElseThrow(()->new RoleNotFoundException("Role with given id"+signupRequestDto.getRoleId()+"does'nt exists"));
        User user = new User();
        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signupRequestDto.getPassword()));
        user.setRoles(List.of(role));
        return UserResponseDto.from(userRepository.save(user));
    }

    @Override
    public UserResponseDto login(LoginRequestDto loginRequestDto)
    {
        User savedUser = userRepository.findByEmail(loginRequestDto.getEmail());
        if(savedUser == null)
        {
            throw new UserNotFoundException("User Not Exsits in DB");
        }
        if (bCryptPasswordEncoder.matches(loginRequestDto.getPassword(),savedUser.getPassword()))
        {
            String userData = savedUser.getEmail()+savedUser.getPassword()+ LocalDateTime.now();
            String token = bCryptPasswordEncoder.encode(userData);
            savedUser.setToken(token);
        }
        else
        {
            throw new InvalidPasswordException("invalid password");
        }

        savedUser = userRepository.save(savedUser);
        return UserResponseDto.from(savedUser);

    }


    @Override
    public UserResponseDto findUserByID(long userId) {
        logger.info("User fetched successfully from db ");
        User savedUser = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Found for id"+userId));
        return UserResponseDto.from(savedUser);
    }

    @Override
    public List<UserResponseDto> findALlUser() {
        logger.info("All users fetched successfully......");
        Iterable<User> users = userRepository.findAll();
        Iterator<User> iterator = users.iterator();
        List<User> usersList = new ArrayList<>();
        while(iterator.hasNext())
        {
            usersList.add(iterator.next());
        }
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User u : usersList)
        {
            userResponseDtoList.add(UserResponseDto.from(u));
        }
        return userResponseDtoList;

    }
    @Override
    public boolean deleteUserById(long userId) {
        User savedUser = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User doesn't exists for given id"+userId));
        logger.info("user does not exists with id in db so exception is thrown");
         userRepository.deleteById(userId);
         logger.info("user deleted successfully.....");
         return true;

    }
    @Override
    public boolean deleteAllUsers() {
        userRepository.deleteAll();
        logger.info("All the users from db deleted successfully.........");
        return true;

    }


}


