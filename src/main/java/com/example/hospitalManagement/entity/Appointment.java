package com.example.hospitalManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private LocalDateTime time;

    private String reason;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable=false,name="patient_id")
    private Patient patient;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="doctor_id",nullable=false)
    private Doctor doctor;

    @OneToOne(mappedBy = "appointment")
    private MedicalRecord medicalRecord;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;
}
