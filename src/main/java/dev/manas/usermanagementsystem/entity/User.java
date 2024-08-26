package dev.manas.usermanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="DEMO_USER")
public class User extends BaseModel {

    private String name;
    private String email;
    private String password;
    private String token;
    @ManyToMany
    private List<Role> roles;


}
