package dev.manas.usermanagementsystem.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role extends BaseModel{
    private String roleName;
    private String description;

}
