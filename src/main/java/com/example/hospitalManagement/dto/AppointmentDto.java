package com.example.hospitalManagement.dto;

import jakarta.annotation.Nonnull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private String id;
    private String time;
    private String reason;
    private Long patientId;
    private String patientName;
    private Long doctorId;
}
