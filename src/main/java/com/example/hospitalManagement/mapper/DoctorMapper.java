package com.example.hospitalManagement.mapper;

import com.example.hospitalManagement.dto.AppointmentDto;
import com.example.hospitalManagement.dto.DoctorDto;
import com.example.hospitalManagement.entity.Appointment;
import com.example.hospitalManagement.entity.Department;
import com.example.hospitalManagement.entity.Doctor;
import com.example.hospitalManagement.service.services.AppointmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {

    public static Doctor toEntity(DoctorDto dto){
        Doctor doctor=new Doctor();
        doctor.setName(dto.getName());
        return doctor;
    }
    public static DoctorDto toDto(Doctor doctor){
        DoctorDto dto=new DoctorDto();
        dto.setId(doctor.getId().toString());
        dto.setName(doctor.getName());
        List<AppointmentDto> appointments=doctor.getAppointments().stream()
                        .map(appointment-> {
                                    AppointmentDto aDto = new AppointmentDto();
                                    aDto.setId(appointment.getId().toString());
                                    aDto.setTime(appointment.getTime().toString());
                                    aDto.setReason(appointment.getReason());
                                    aDto.setPatientId(appointment.getPatient().getId());
                                    aDto.setPatientName(appointment.getPatient().getName());
                                    aDto.setDoctorId(appointment.getDoctor().getId());
                                    return aDto;
                                }).collect(Collectors.toList());
        dto.setAppointments(appointments);
        return dto;
    }
}
