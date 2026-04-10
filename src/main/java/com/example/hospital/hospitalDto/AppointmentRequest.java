package com.example.hospital.hospitalDto;

import lombok.Data;

@Data
public class AppointmentRequest {
    private Long doctorId;
    private String date;
    private String time;
    private String bloodGroup;
    private String reason;
    private String dob;
    private String address; 
}
