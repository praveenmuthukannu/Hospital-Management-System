package com.example.hospital.hospitalDto;

import lombok.Data;

@Data
public class DoctorDTO {
	private long id;
    private String name;
    private String specialization;
    private int experience;
    private double fee;

  
    public DoctorDTO(long id,String name, String specialization, int experience, double fee) {
    	this.id=id;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
        this.fee = fee;
    }
}
