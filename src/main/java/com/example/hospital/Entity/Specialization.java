package com.example.hospital.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    
    public void setName(String name) { this.name = name; }
}
