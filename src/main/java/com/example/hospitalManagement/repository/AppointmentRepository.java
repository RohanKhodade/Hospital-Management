package com.example.hospitalManagement.repository;

import com.example.hospitalManagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndTime(Long doctorId, LocalDateTime time);
    List<Appointment> findAllByDoctorIdAndTime(Long doctorId, LocalDateTime time);
}
