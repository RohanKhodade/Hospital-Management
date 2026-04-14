package com.example.hospitalManagement.dto;

import com.example.hospitalManagement.entity.Insurance;
import com.example.hospitalManagement.entity.Patient;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsuranceDto {
    private String policyNumber;
    private String provider;
    private String createdAt;
    private String validity;
    private String patientId;
    private String patientName;
    private String patientEmail;
}
