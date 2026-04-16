package com.example.hospitalManagement.controller;

import com.example.hospitalManagement.dto.CompleteAppointmentDto;
import com.example.hospitalManagement.dto.DoctorDto;
import com.example.hospitalManagement.dto.MedicalRecordDto;
import com.example.hospitalManagement.dto.ScheduleDto;
import com.example.hospitalManagement.service.services.DoctorService;
import com.example.hospitalManagement.service.services.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    private final MedicalRecordService medicalRecordService;
    public DoctorController(DoctorService doctorService,
                            MedicalRecordService medicalRecordService){
        this.doctorService=doctorService;
        this.medicalRecordService=medicalRecordService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> createDoctor(@RequestBody DoctorDto dto){
        return new ResponseEntity<>(doctorService.createDoctor(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long doctorId){
        return new ResponseEntity<>(doctorService.deleteDoctor(doctorId),HttpStatus.OK);
    }

    @GetMapping("/getAppointment/{id}")
    public ResponseEntity<DoctorDto> getAppointments(@PathVariable Long id){
        return new ResponseEntity<>(doctorService.getAppointments(id),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    @PostMapping("/createSchedule/{doctor_id}")
    public ResponseEntity<String> createSchedule(@RequestBody ScheduleDto dto,
                                                 @PathVariable Long doctor_id){
        return new ResponseEntity<>(doctorService.createSchedule(dto,doctor_id),
                HttpStatus.OK);
    }
    @GetMapping("/MedicalRecords/{doctorId}")
    public ResponseEntity<List<MedicalRecordDto>> getMedicalRecords(@PathVariable Long doctorId){
        return new ResponseEntity<>(medicalRecordService.getAllDoctorRecords(doctorId),
                HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping("/complete/Appointment/{appointmentId}")
    public ResponseEntity<String> completeAppointment(@PathVariable Long appointmentId,
                                                      @RequestBody CompleteAppointmentDto dto){
        return new ResponseEntity<>(doctorService.
                completeAppointment(appointmentId,
                        dto.getPrescription(),
                        dto.getNotes()),HttpStatus.OK);
    }

}