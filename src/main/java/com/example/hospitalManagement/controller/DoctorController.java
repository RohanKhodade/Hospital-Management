package com.example.hospitalManagement.controller;

import com.example.hospitalManagement.dto.DoctorDto;
import com.example.hospitalManagement.service.services.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService){
        this.doctorService=doctorService;
    }
    @PostMapping("/create")
    public ResponseEntity<String> createDoctor(@RequestBody DoctorDto dto){
        return new ResponseEntity<>(doctorService.createDoctor(dto), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long doctorId){
        return new ResponseEntity<>(doctorService.deleteDoctor(doctorId),HttpStatus.OK);
    }

    @GetMapping("/getAppointment/{id}")
    public ResponseEntity<DoctorDto> getAppointments(@PathVariable Long id){
        return new ResponseEntity<>(doctorService.getAppointments(id),HttpStatus.OK);
    }
}
