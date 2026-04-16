package com.example.hospitalManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="patient_id",nullable=false)
    private Patient patient;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="doctor_id",nullable=false)
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name="appointment_id",nullable=false)
    private Appointment appointment;

    @Column(nullable=false)
    private String diagnosis;

    @Column(nullable=false)
    private String prescription;

    private String note;

}
