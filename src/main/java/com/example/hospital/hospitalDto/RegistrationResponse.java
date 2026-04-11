package com.example.hospital.hospitalDto;

import com.example.hospital.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResponse {
    private String message;
    private User user; // This will show the created user in Postman
}
