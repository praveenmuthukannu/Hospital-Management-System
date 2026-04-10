package com.example.hospital.hospitalService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital.Entity.Doctor;
import com.example.hospital.Entity.Specialization;
import com.example.hospital.Entity.User;
import com.example.hospital.hospitalDto.DoctorDTO;
import com.example.hospital.hospitalDto.DoctorRegistrationRequest;
import com.example.hospital.hospitalRepository.DoctorRepository;
import com.example.hospital.hospitalRepository.SpecializationRepository;
import com.example.hospital.hospitalRepository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    DoctorRepository  doctorRepository;
    
    @Autowired 
    private SpecializationRepository specializationRepository;

    public Boolean Checkregister(User user) {
    	if(userRepository.existsByEmail(user.getEmail())) return true; 
      return false;
    }
    
    public void  register(User user) {
    	userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    @Transactional
    public void registerDoctorProfile(DoctorRegistrationRequest req) {
        // 1. Create and save the User (for login credentials)
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setPassword(req.getPassword()); // In a real app, use password encoder
        user.setRole(User.UserRole.doctor);
        user = userRepository.save(user);

        // 2. Resolve Specialization
        Specialization spec = specializationRepository.findByName(req.getSpecialization())
                .orElseGet(() -> {
                    Specialization newSpec = new Specialization();
                    newSpec.setName(req.getSpecialization());
                    return specializationRepository.save(newSpec);
                });

        // 3. Create and save the Doctor entity
        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setSpecialization(spec);
        doctor.setExperience(req.getExperience() != null ? req.getExperience() : 0);
        doctor.setFee(req.getFee() != null ? req.getFee() : 0L);
        
        doctorRepository.save(doctor);
    }
    
    
}