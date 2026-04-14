package com.example.hospitalManagement.service.services;

import com.example.hospitalManagement.dto.InsuranceDto;
import com.example.hospitalManagement.entity.Insurance;

public interface InsuranceService {
    String assignInsuranceToPatient(InsuranceDto insuranceDto, Long patientId);
}
