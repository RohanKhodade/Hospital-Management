package com.example.hospitalManagement.controller;

import com.example.hospitalManagement.dto.AppointmentDto;
import com.example.hospitalManagement.service.services.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService=appointmentService;
    }
    @PostMapping("/create/{patientId}/{doctorId}")
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentDto appointmentDto,
                                                    @PathVariable Long patientId,
                                                    @PathVariable Long doctorId){
        return new ResponseEntity<>(
                appointmentService.createAppointment(appointmentDto,patientId,doctorId),
                HttpStatus.OK);
    }

}
