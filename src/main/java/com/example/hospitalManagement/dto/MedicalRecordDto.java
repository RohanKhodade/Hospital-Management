package com.example.hospitalManagement.dto;

import com.example.hospitalManagement.entity.Patient;
import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDto {

    private String time;
    private String patientName;
    private String patientEmail;
    private String doctorName;
    private String diagnosis;
    private String prescription;
    private String notes;
}