package com.example.hospital.hospitalRepository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.hospital.Entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
	
	
	@Query("SELECT s FROM Specialization s WHERE s.name = :name")
    Optional<Specialization> findByName(@Param("name") String name);
	
}
