package com.example.hospital.hospitalRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.hospital.Entity.Appointment;
import com.example.hospital.Entity.Patient;
import com.example.hospital.hospitalDto.AdminAppointmentDto;

import com.example.hospital.hospitalDto.PatientAppointmentDto;

import jakarta.transaction.Transactional;

import com.example.hospital.Entity.Doctor;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByPatient(Patient patient);

	List<Appointment> findByDoctor(Doctor doctor);

	@Query("SELECT new com.example.hospital.hospitalDto.PatientAppointmentDto(a.id, d.user.name, a.appt_date, a.appt_time, a.reason, str(a.status)) "
			+ "FROM Appointment a " + "JOIN a.doctor d " + "WHERE a.patient.user.id = :userId")
	List<PatientAppointmentDto> findAppointmentsByUserId(@Param("userId") Long userId);

	@Query("SELECT new com.example.hospital.hospitalDto.PatientAppointmentDto(a.id, u.name, a.appt_date, a.appt_time, a.reason, str(a.status)) "
			+ "FROM Appointment a JOIN a.patient p JOIN p.user u " + "WHERE a.doctor.user.id = :userId "
			+ "AND a.status = com.example.hospital.Entity.Appointment.AppointmentStatus.scheduled")
	List<PatientAppointmentDto> findAppointmentsByDoctorUserId(@Param("userId") Long userId);

	@Query("SELECT new com.example.hospital.hospitalDto.AdminAppointmentDto(p.user.name, d.user.name, a.appt_date,a.reason, a.status) "
			+ "FROM Appointment a " + "JOIN a.patient p " + "JOIN a.doctor d")
	List<AdminAppointmentDto> findAllAdminAppointments();

	@Modifying
	@Transactional
	@Query("DELETE FROM Appointment a WHERE a.doctor.id = :doctorId")
	void deleteByDoctorId(Long doctorId);

}
