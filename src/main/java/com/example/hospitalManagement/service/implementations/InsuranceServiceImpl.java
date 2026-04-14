package com.example.hospitalManagement.service.implementations;

import com.example.hospitalManagement.dto.InsuranceDto;
import com.example.hospitalManagement.entity.Insurance;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.exceptions.EntityNotFoundException;
import com.example.hospitalManagement.mapper.InsuranceMapper;
import com.example.hospitalManagement.repository.InsuranceRepository;
import com.example.hospitalManagement.repository.PatientRepository;
import com.example.hospitalManagement.service.services.InsuranceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InsuranceServiceImpl implements InsuranceService {
    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;
    public InsuranceServiceImpl (PatientRepository patientRepository,
                                 InsuranceRepository insuranceRepository){
        this.patientRepository=patientRepository;
        this.insuranceRepository=insuranceRepository;
    }

    @Transactional
    @Override
    public String assignInsuranceToPatient(InsuranceDto insuranceDto, Long patientId){
        Patient patient=patientRepository.findById(patientId).orElseThrow(
                ()->new EntityNotFoundException("patient", patientId)
        );
        Insurance insurance= InsuranceMapper.toEntity(insuranceDto);
        insurance.setPatient(patient);
        patient.setInsurance(insurance);
        insuranceRepository.save(insurance);
        return "insurance assigned to patient with id: "+patientId;
    }
}
