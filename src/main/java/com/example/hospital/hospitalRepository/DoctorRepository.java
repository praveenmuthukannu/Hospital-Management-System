package com.example.hospital.hospitalRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hospital.Entity.Doctor;
import com.example.hospital.hospitalDto.DoctorDTO;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Query("SELECT new com.example.hospital.hospitalDto.DoctorDTO(u.id,u.name, s.name, d.experience, d.fee) "
			+ "FROM Doctor d " + "JOIN d.user u " + "JOIN d.specialization s")
	List<DoctorDTO> findAllDoctorDetails();

	Optional<Doctor> findByUserId(Long userId);

	List<Doctor> findBySpecializationId(Integer specId);

	List<Doctor> findBySpecialization(String spec);
}
