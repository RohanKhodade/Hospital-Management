package com.example.hospitalManagement.mapper;

import com.example.hospitalManagement.dto.MedicalRecordDto;
import com.example.hospitalManagement.entity.MedicalRecord;
import org.springframework.stereotype.Component;

@Component
public class MedicalRecordMapper {

    public static MedicalRecordDto toDto(MedicalRecord record){
        MedicalRecordDto dto=new MedicalRecordDto();
        dto.setPatientName(record.getPatient().getName());
        dto.setPatientEmail(record.getPatient().getEmail());
        dto.setDoctorName(record.getDoctor().getName());
        dto.setDiagnosis(record.getDiagnosis());
        dto.setPrescription(record.getPrescription());
        dto.setNotes(record.getNote());
        return dto;
    }
}
