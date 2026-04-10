package com.example.hospital.hospitalController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.example.hospital.hospitalDto.AdminAppointmentDto;
import com.example.hospital.hospitalDto.AppointmentRequest;
import com.example.hospital.hospitalDto.DoctorDTO;
import com.example.hospital.hospitalDto.PatientAppointmentDto;
import com.example.hospital.hospitalRepository.AppointmentRepository;
import com.example.hospital.hospitalRepository.DoctorRepository;
import com.example.hospital.hospitalService.AppointmentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/appointment/")
@CrossOrigin
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
  
    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;


    @PostMapping("/book-appointment")
    @ResponseBody
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentRequest request, HttpSession session) {
        
        Long userId = (Long)session.getAttribute("userId");
        
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired. Please login.");
        }

        try {
            appointmentService.createAppointment(request, userId);
            return ResponseEntity.ok("Appointment confirmed successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/book-appointment")
	 public String showAppointmentForm(Model model) {
	     
	     List<DoctorDTO> doctors = doctorRepository.findAllDoctorDetails();
	     
	     
	     model.addAttribute("doctorList", doctors);
	     
	     
	     return "patient/book-appointment";
	 }
    
    @GetMapping("/my-appointments")
    public String showAppointmentList(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        
        if (userId == null) {
            return "redirect:/api/auth/login";
        }

        // Use the repository method we just created
        List<PatientAppointmentDto> appointments = appointmentRepository.findAppointmentsByUserId(userId);
        
        model.addAttribute("listAppointment", appointments);
        return "patient/my-appointments"; // Ensure this matches your HTML file name
    }
    
    @PostMapping("/update-status")
    public String updateAppointmentStatus(@RequestParam("id") Long id, 
                                         @RequestParam("status") String status) {
        appointmentService.updateStatus(id, status);
        return "redirect:/api/doctor/my-appointments";
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok("Appointment cancelled successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found.");
        }
    }
    
    @GetMapping("/admin/appointments")
    public String showAllAppointments(Model model) {
        List<AdminAppointmentDto> appointments = appointmentRepository.findAllAdminAppointments();
        model.addAttribute("allAppts", appointments);
        return "admin/admin-appointments"; 
    }
    
   
    
}