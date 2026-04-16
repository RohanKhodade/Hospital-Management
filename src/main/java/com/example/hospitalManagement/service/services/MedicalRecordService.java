package com.example.hospitalManagement.service.services;


import com.example.hospitalManagement.dto.MedicalRecordDto;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecordDto getMedicalRecord(Long recordId);
    List<MedicalRecordDto> getAllPatientRecords(Long patientId);
    List<MedicalRecordDto> getAllDoctorRecords(Long doctorId);
}
