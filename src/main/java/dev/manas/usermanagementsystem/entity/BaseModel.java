package dev.manas.usermanagementsystem.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


}
