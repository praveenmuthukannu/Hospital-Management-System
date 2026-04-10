package com.example.hospital.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String dob;
    private String blood_group;
    private String address;

   
}
