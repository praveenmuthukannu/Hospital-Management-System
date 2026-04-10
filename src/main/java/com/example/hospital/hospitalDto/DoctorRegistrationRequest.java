package com.example.hospital.hospitalDto;

import lombok.Data;

@Data
public class DoctorRegistrationRequest {
	private String name;
    private String email;
    private String phone;
    private String password;
    private String specialization;
    private Integer experience; 
    private Long fee;
    
    
	public DoctorRegistrationRequest(String name, String email, String phone, String password, String specialization,
			Integer experience, Long fee) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.specialization = specialization;
		this.experience = experience;
		this.fee = fee;
	}
    
    
}
