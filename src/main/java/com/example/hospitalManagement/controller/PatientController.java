package com.example.hospitalManagement.controller;

import com.example.hospitalManagement.dto.InsuranceDto;
import com.example.hospitalManagement.dto.PatientDto;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.service.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;
    public PatientController(PatientService patientService){
        this.patientService=patientService;
    }
    @GetMapping("{id}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable Long id){
        return new ResponseEntity<>(
                patientService.getPatient(id),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PatientDto>> getAll(){
        return new ResponseEntity<>(patientService.getAllPatients(),
                HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createPatient(@RequestBody PatientDto patientDto){
        return new ResponseEntity<>(patientService.createPatient(patientDto),HttpStatus.OK);
    }
    @GetMapping("/{patientId}/insurance")
    public ResponseEntity<InsuranceDto> getInsurance(@PathVariable Long patientId){
        return new ResponseEntity<>(patientService.getInsurance(patientId),HttpStatus.OK);
    }
}