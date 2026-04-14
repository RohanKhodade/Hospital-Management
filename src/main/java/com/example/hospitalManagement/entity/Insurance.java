package com.example.hospitalManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private Long policyNumber;

    @Column(nullable=false)
    private String Provider;

    @Column(nullable=false)
    private LocalDate validity;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt=LocalDateTime.now();
    }

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="patient_id",nullable=false)
    private Patient patient;
}
