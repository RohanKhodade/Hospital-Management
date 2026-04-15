package com.example.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="doctor_id", nullable=false)
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private DayOfWeek dayOfWeek;

    @Column(nullable=false)
    private LocalTime startTime;

    @Column(nullable=false)
    private LocalTime endTime;

    @Column(nullable=false)
    private int slotDurationMinutes;
}