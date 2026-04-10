package com.example.hospital.hospitalService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hospital.Entity.Doctor;
import com.example.hospital.Entity.Specialization;
import com.example.hospital.Entity.User;
import com.example.hospital.hospitalDto.DoctorRegistrationRequest;
import com.example.hospital.hospitalRepository.AppointmentRepository;
import com.example.hospital.hospitalRepository.DoctorRepository;
import com.example.hospital.hospitalRepository.SpecializationRepository;
import com.example.hospital.hospitalRepository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private SpecializationRepository specializationRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Transactional
    public void updateDoctorProfile(Long id, DoctorRegistrationRequest req) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Update associated User details
        User user = existingDoctor.getUser();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        userRepository.save(user);

        // Update Specialization
        Specialization spec = specializationRepository.findByName(req.getSpecialization())
                .orElseGet(() -> {
                    Specialization newSpec = new Specialization();
                    newSpec.setName(req.getSpecialization());
                    return specializationRepository.save(newSpec);
                });

        // Update Doctor details
        existingDoctor.setSpecialization(spec);
        existingDoctor.setExperience(req.getExperience());
        existingDoctor.setFee(req.getFee());
        
        doctorRepository.save(existingDoctor);
    }
    
    @Transactional
    public void deleteDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        
        // 1. Delete all appointments associated with this doctor first
        appointmentRepository.deleteByDoctorId(id); 
        
        // 2. Get the associated user
        User user = doctor.getUser();
        
        // 3. Delete the doctor record
        doctorRepository.delete(doctor);
        
        // 4. Finally, delete the user record
        if (user != null) {
            userRepository.delete(user);
        }
    }
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    public Doctor getById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }
}
