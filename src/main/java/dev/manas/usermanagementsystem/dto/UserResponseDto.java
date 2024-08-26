package dev.manas.usermanagementsystem.dto;

import dev.manas.usermanagementsystem.entity.Role;
import dev.manas.usermanagementsystem.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String email;
    private List<RoleResponseDto> roles;
    public static UserResponseDto from(User user)
    {
        UserResponseDto userResponseDto  = new UserResponseDto();
        userResponseDto.name = user.getName();
        userResponseDto.email = user.getEmail();
        userResponseDto.roles = user.getRoles().stream().map(role -> {
            RoleResponseDto roleResponseDto = new RoleResponseDto();
            roleResponseDto.setRoleDesc(role.getDescription());
            roleResponseDto.setRolename(role.getRoleName());
            return roleResponseDto;
        }).collect(Collectors.toList());

        return userResponseDto;
    }

}
