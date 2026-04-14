package com.example.hospitalManagement.mapper;

import com.example.hospitalManagement.dto.InsuranceDto;
import com.example.hospitalManagement.dto.PatientDto;
import com.example.hospitalManagement.entity.Insurance;
import com.example.hospitalManagement.service.services.PatientService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class InsuranceMapper {

    private final PatientService patientService;
    public InsuranceMapper(PatientService patientService){
        this.patientService=patientService;
    }
    public static InsuranceDto toDto(Insurance insurance){
        InsuranceDto dto=new InsuranceDto();
        dto.setPatientId(insurance.getPatient().getId().toString());
        dto.setPatientName(insurance.getPatient().getName());
        dto.setPatientEmail(insurance.getPatient().getEmail());
        dto.setPolicyNumber(insurance.getPolicyNumber().toString());
        dto.setProvider(insurance.getProvider());
        dto.setCreatedAt(insurance.getCreatedAt().toString());
        dto.setValidity(insurance.getValidity().toString());
        return dto;
    }

    public static Insurance toEntity(InsuranceDto dto){
        Insurance insurance=new Insurance();
        insurance.setPolicyNumber(Long.parseLong(dto.getPolicyNumber()));
        insurance.setProvider(dto.getProvider());
        insurance.setValidity(LocalDate.parse(dto.getValidity()));
        return insurance;
    }
}
