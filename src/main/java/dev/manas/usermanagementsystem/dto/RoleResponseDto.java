package dev.manas.usermanagementsystem.dto;

import dev.manas.usermanagementsystem.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDto {
    private String rolename;
    private String roleDesc;
    public static RoleResponseDto from (Role role)
    {
        RoleResponseDto roleResponseDto = new RoleResponseDto();
        roleResponseDto.setRolename(role.getRoleName());
        roleResponseDto.setRoleDesc(role.getDescription());
        return roleResponseDto;
    }

}
