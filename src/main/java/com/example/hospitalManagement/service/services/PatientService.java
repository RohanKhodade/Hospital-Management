package com.example.hospitalManagement.service.services;

import com.example.hospitalManagement.dto.InsuranceDto;
import com.example.hospitalManagement.dto.PatientDto;
import com.example.hospitalManagement.entity.Patient;

import java.util.List;

public interface PatientService {
    PatientDto getPatient(Long id);
    List<PatientDto> getAllPatients();
    String createPatient(PatientDto patient);
    InsuranceDto getInsurance(Long patientId);
}
