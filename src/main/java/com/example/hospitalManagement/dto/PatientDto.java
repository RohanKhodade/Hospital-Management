package com.example.hospitalManagement.dto;

import com.example.hospitalManagement.entity.Appointment;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class PatientDto {

    public PatientDto(){

    };
    public PatientDto(String id,
                      String name,
                      String email,
                      String birthdate){
        this.id=id;
        this.name=name;
        this.email=email;
        this.birthdate=birthdate;
    }
    private String id;
    private String name;
    private String email;
    private String birthdate;

    private String username;
    private String password;

    private ArrayList<Appointment> appointments;
}