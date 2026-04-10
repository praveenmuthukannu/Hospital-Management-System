package com.example.hospital.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING) 
    private UserRole role;
    private String phone;
    
    public enum UserRole {
        patient,
        doctor,
        admin
    }
}
