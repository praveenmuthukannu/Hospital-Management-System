package com.example.hospital.hospitalController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.hospital.Entity.*;
import com.example.hospital.hospitalDto.DoctorRegistrationRequest;
import com.example.hospital.hospitalService.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/auth/")
public class AuthController {

	@Autowired
	private AuthService authService;

	@GetMapping("/")
	public String home() {
		return "/home/home";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "/home/login";
	}

	@GetMapping("/register")
	public String registerPage() {

		return "/home/register";
	}

	@GetMapping("/patient-dashboard")
	public String patientdashboard(Model model, HttpSession session) {
		String name = (String) session.getAttribute("userName");
		model.addAttribute("name", name);
		return "/patient/patient-dashboard";
	}

	@GetMapping("/doctor-dashboard")
	public String doctordashboard(Model model, HttpSession session) {
		String name = (String) session.getAttribute("userName");
		model.addAttribute("name", name);
		return "/doctor/doctor-dashboard";
	}

	@GetMapping("/admin-dashboard")
	public String admindashboard(Model model, HttpSession session) {
		String name = (String) session.getAttribute("userName");
		model.addAttribute("name", name);
		return "/admin/admin-dashboard";
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		if (authService.Checkregister(user)) {

			return ResponseEntity.badRequest().body("Email already exists");
		}
		user.setRole(User.UserRole.patient);
		authService.register(user);

		return ResponseEntity.ok("Success");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Map<String, String> loginData, HttpSession session) {
		String email = loginData.get("email");
		String password = loginData.get("password");
		String selectedRole = loginData.get("role");

		User validUser = authService.login(email, password);

		if (validUser != null) {

			String dbRole = validUser.getRole().name().toLowerCase();

			if (!dbRole.equals(selectedRole.toLowerCase())) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body("Access Denied: You cannot login as " + selectedRole + " with this account.");
			}

			
			session.setAttribute("userName", validUser.getName());
			session.setAttribute("userEmail", validUser.getEmail());
			session.setAttribute("userId", validUser.getId());
			session.setAttribute("userRole", dbRole);

			String redirectUrl = "/api/auth/" + dbRole + "-dashboard";

			return ResponseEntity.ok(redirectUrl);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}

	@PostMapping("/add-doctor")
	@ResponseBody
	public ResponseEntity<String> registerDoctor(@RequestBody DoctorRegistrationRequest request) {
		try {
			
			authService.registerDoctorProfile(request);
			return ResponseEntity.ok("Doctor registered successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
		}
	}

	
	@GetMapping("add-doctor")
	public String showAddDoctorPage() {
		return "admin/add-doctor";
	}
}
