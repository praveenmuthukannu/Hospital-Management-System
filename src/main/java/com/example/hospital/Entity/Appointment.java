package com.example.hospital.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    
    private String appt_date;
    private String appt_time;
    private String reason;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status = AppointmentStatus.scheduled; 

    
    public enum AppointmentStatus {
        scheduled, completed, cancelled
    }

   
}
