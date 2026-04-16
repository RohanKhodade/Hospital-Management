package com.example.hospitalManagement.entity;

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

    @ManyToOne
    @JoinColumn(name="patient_id",nullable=false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctor_id",nullable=false)
    private Doctor doctor;

    @Column(nullable=false)
    private String diagnosis;

    @Column(nullable=false)
    private String prescription;

    private String note;


}
