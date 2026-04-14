package com.example.hospitalManagement.service.services;


import com.example.hospitalManagement.dto.AppointmentDto;
import com.example.hospitalManagement.entity.Appointment;

public interface AppointmentService {
    String createAppointment(AppointmentDto dto, Long patientId, Long doctorId);
}
