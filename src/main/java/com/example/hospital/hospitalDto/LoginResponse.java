package com.example.hospital.hospitalDto;

import com.example.hospital.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String redirectUrl;
    private String message;
    private User user; // This will hold the full user data for Postman
}