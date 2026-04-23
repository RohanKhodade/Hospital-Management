package com.example.hospitalManagement.controller;

import com.example.hospitalManagement.dto.InsuranceDto;
import com.example.hospitalManagement.dto.MedicalRecordDto;
import com.example.hospitalManagement.dto.PatientDto;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.service.services.MedicalRecordService;
import com.example.hospitalManagement.service.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;
    private final MedicalRecordService medicalRecordService;
    public PatientController(PatientService patientService,
                             MedicalRecordService medicalRecordService){
        this.patientService=patientService;
        this.medicalRecordService=medicalRecordService;
    }
    @GetMapping("{id}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable Long id){
        return new ResponseEntity<>(
                patientService.getPatient(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatientDto>> getAll(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue="10") int pageSize
    ){
        return new ResponseEntity<>(patientService.getAllPatients(pageNumber,pageSize),
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

    @PreAuthorize("hasRole('ADMIN') or hasRole('PATIENT') or hasRole('DOCTOR')")
    @GetMapping("/MedicalRecords/{patientId}")
    public ResponseEntity<List<MedicalRecordDto>> getMedicalRecords(@PathVariable Long patientId){
        return   new ResponseEntity<>(medicalRecordService.getAllPatientRecords(patientId),
                HttpStatus.OK);
    }
}