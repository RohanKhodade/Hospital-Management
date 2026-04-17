package com.example.hospitalManagement.repository;

import com.example.hospitalManagement.dto.ScheduleDto;
import com.example.hospitalManagement.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface DoctorScheduleRepo extends JpaRepository<DoctorSchedule, Long> {
    Optional<DoctorSchedule> findByDoctorIdAndDayOfWeek(Long doctorId, DayOfWeek day);
    List<DoctorSchedule> findAllByDoctorId(Long doctorId);
}
