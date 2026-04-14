package com.example.hospitalManagement.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private String id;
    private String name;

    private String username;
    private String password;

    private List<AppointmentDto> appointments;
    private List<DepartmentDto> departments;
}