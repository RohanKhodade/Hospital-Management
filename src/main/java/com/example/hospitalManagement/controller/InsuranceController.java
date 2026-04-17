package com.example.hospitalManagement.controller;

import com.example.hospitalManagement.dto.InsuranceDto;
import com.example.hospitalManagement.service.services.InsuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;
    public InsuranceController(InsuranceService insuranceService){
        this.insuranceService=insuranceService;
    }

    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('ADMIN')")
    @PostMapping("/assignToPatient/{patientId}")
    public ResponseEntity<String> assignInsuranceToPatient(@RequestBody InsuranceDto insuranceDto,
                                                           @PathVariable Long patientId){
        return new ResponseEntity<>(
                insuranceService.assignInsuranceToPatient(insuranceDto,patientId),
                HttpStatus.OK);
    }
}