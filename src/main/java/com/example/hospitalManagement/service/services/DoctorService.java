package com.example.hospitalManagement.service.services;

import com.example.hospitalManagement.dto.DoctorDto;
import com.example.hospitalManagement.dto.ScheduleDto;
import com.example.hospitalManagement.entity.DoctorSchedule;

public interface DoctorService {
    String createDoctor(DoctorDto dto);
    String deleteDoctor(Long doctorId);
    DoctorDto getAppointments(Long doctorId);
    String createSchedule(ScheduleDto dto, Long doctor_id);
}