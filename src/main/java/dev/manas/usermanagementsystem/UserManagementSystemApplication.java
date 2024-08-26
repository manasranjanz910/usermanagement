package dev.manas.usermanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class UserManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementSystemApplication.class, args);
    }
}
