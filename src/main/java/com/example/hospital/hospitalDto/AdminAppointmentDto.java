package com.example.hospital.hospitalDto;


import com.example.hospital.Entity.Appointment.AppointmentStatus;

import lombok.Data;

@Data
public class AdminAppointmentDto {
	private String patientName;
    private String doctorName;
    private String date;
    private String reason;
    private AppointmentStatus status;

    public AdminAppointmentDto(String patientName, String doctorName, String date,String reason ,AppointmentStatus status) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.reason=reason;
        this.status = status;
    }
}
