package com.example.hospitalManagement.mapper;

import com.example.hospitalManagement.dto.PatientDto;
import com.example.hospitalManagement.entity.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class PatientMapper {

    public static PatientDto toDto(Patient patient){
        PatientDto dto=new PatientDto();
        dto.setId(patient.getId().toString());
        dto.setName(patient.getName());
        dto.setEmail(patient.getEmail());
        dto.setBirthdate(patient.getBirthdate().toString());
        dto.setAppointments(new ArrayList<>(patient.getAppointments()));
        dto.setUsername(patient.getUser().getUsername());
        return dto;
    }
    public static Patient toEntity(PatientDto dto){
        Patient patient=new Patient();
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setBirthdate(LocalDate.parse(dto.getBirthdate()));
        return patient;
    }
}
