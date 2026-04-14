package com.example.hospitalManagement.mapper;

import com.example.hospitalManagement.dto.AppointmentDto;
import com.example.hospitalManagement.entity.Appointment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppointmentMapper {

    public static AppointmentDto toDto(Appointment appointment){
        AppointmentDto dto=new AppointmentDto();
        dto.setTime(appointment.getTime().toString());
        dto.setReason(appointment.getReason());
        dto.setDoctorId(appointment.getDoctor().getId());
        dto.setPatientId(appointment.getPatient().getId());
        return dto;
    }
    public static Appointment toEntity(AppointmentDto dto){
        Appointment appointment= new Appointment();
        appointment.setTime(LocalDateTime.parse(dto.getTime()));
        appointment.setReason(dto.getReason());
        return appointment;
    }
}
