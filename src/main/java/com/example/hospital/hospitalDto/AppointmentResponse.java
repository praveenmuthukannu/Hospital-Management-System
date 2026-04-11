package com.example.hospital.hospitalDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentResponse {
    private String message;
    private Long appointmentId; // Helpful for Postman to see the generated ID
    private String doctorName;
    private String appointmentDate;
}
