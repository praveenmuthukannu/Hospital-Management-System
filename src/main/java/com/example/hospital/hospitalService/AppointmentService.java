package com.example.hospital.hospitalService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hospital.Entity.Appointment;
import com.example.hospital.Entity.Patient;
import com.example.hospital.Entity.User;
import com.example.hospital.hospitalDto.AppointmentRequest;
import com.example.hospital.Entity.Doctor;
import com.example.hospital.hospitalRepository.AppointmentRepository;
import com.example.hospital.hospitalRepository.DoctorRepository;
import com.example.hospital.hospitalRepository.PatientRepository;
import com.example.hospital.hospitalRepository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired private PatientRepository patientRepository;
    @Autowired private DoctorRepository doctorRepository;
    @Autowired private UserRepository userRepository;

    public Appointment save(Appointment appointment) {
       
        appointment.setStatus(Appointment.AppointmentStatus.scheduled);
        return appointmentRepository.save(appointment);
    }
    
    public List<Appointment> getByPatient(Patient patient) {
        return appointmentRepository.findByPatient(patient);
    }

    public List<Appointment> getByDoctor(Doctor doctor) {
        return appointmentRepository.findByDoctor(doctor);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public void updateStatus(Long id, String status) {
        Appointment appt = appointmentRepository.findById(id).orElse(null);
        if (appt != null) {
            
            appt.setStatus(Appointment.AppointmentStatus.valueOf(status.toLowerCase()));
            appointmentRepository.save(appt);
        }
    }
    
    @Transactional 
    public void createAppointment(AppointmentRequest request, Long userId) {
       
        Patient patient = patientRepository.findByUserId(userId).orElse(null);
        if (patient == null) {
            patient = new Patient();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            patient.setUser(user);
            patient.setBlood_group(request.getBloodGroup());
            patient.setDob(request.getDob());
            patient.setAddress(request.getAddress());
            patient = patientRepository.save(patient);
        }

       
        List<Appointment> previousAppointments = appointmentRepository.findByPatient(patient);
        for (Appointment oldAppt : previousAppointments) {
            if (oldAppt.getStatus() == Appointment.AppointmentStatus.scheduled) {
                oldAppt.setStatus(Appointment.AppointmentStatus.completed);
            }
        }
        appointmentRepository.saveAll(previousAppointments);

       
        Doctor doctor = doctorRepository.findByUserId(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppt_date(request.getDate());
        appointment.setAppt_time(request.getTime());
        appointment.setReason(request.getReason());
        appointment.setStatus(Appointment.AppointmentStatus.scheduled);

        appointmentRepository.save(appointment);
    }
    
    public void deleteAppointment(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Appointment with ID " + id + " does not exist.");
        }
    }

	
}
