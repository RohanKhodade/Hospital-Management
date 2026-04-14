package com.example.hospitalManagement.service.services;

import com.example.hospitalManagement.dto.DoctorDto;

public interface DoctorService {
    String createDoctor(DoctorDto dto);
    String deleteDoctor(Long doctorId);
    DoctorDto getAppointments(Long doctorId);
}
