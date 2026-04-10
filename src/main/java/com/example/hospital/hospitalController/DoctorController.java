package com.example.hospital.hospitalController;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.hospital.Entity.Doctor;
import com.example.hospital.hospitalDto.DoctorDTO;
import com.example.hospital.hospitalDto.DoctorRegistrationRequest;
import com.example.hospital.hospitalDto.PatientAppointmentDto;
import com.example.hospital.hospitalRepository.AppointmentRepository;
import com.example.hospital.hospitalRepository.DoctorRepository;
import com.example.hospital.hospitalService.DoctorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/doctor/")
public class DoctorController {

    
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private DoctorService  doctorService;


	 
	 
	 @GetMapping("/doctors")
	 public String viewDoctors(Model model) {
	     List<DoctorDTO> doctors = doctorRepository.findAllDoctorDetails();
	     model.addAttribute("listDoctor", doctors);
	  
	     
	     return "patient/view-doctors";
	 }

	 @GetMapping("/my-appointments")
	 public String showDoctorAppointments(Model model, HttpSession session) {
	     Long userId = (Long) session.getAttribute("userId");
	     
	     if (userId == null) {
	         return "redirect:/api/auth/login";
	     }

	    
	     List<PatientAppointmentDto> appointments = appointmentRepository.findAppointmentsByDoctorUserId(userId);
	     
	     model.addAttribute("listAppointment", appointments);
	     return "doctor/doctor-appointments"; 
	 }

    @GetMapping("/profile")
    public String profile() {
        return "doctor-profile";
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "doctor-schedule";
    }
    
 
    @GetMapping("/edit-doctor/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id:" + id));
        model.addAttribute("doctor", doctor);
        return "admin/edit-doctor"; 
    }

    
    @PutMapping("/update/{id}") 
    @ResponseBody
    public ResponseEntity<String> updateDoctor(@PathVariable Long id, @RequestBody DoctorRegistrationRequest updateRequest) {
        try {
            doctorService.updateDoctorProfile(id, updateRequest);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @GetMapping("admin-doctor") 
    public String manageDoctor(Model model) {
      
        List<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors); 
        return "admin/admin-doctors"; 
    }
    
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        try {
            doctorService.deleteDoctorById(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error: " + e.getMessage());
        }
    }
}
