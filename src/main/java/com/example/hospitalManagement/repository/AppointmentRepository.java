package com.example.hospitalManagement.repository;

import com.example.hospitalManagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndTime(Long doctorId, LocalDateTime time);
}
