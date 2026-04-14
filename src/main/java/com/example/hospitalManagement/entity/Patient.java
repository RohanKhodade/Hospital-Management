package com.example.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.action.internal.OrphanRemovalAction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patient {

    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable=false)
    private String name;

    @Column(name="email",nullable=false)
    private String email;

    @Column(name="birthdate")
    private LocalDate birthdate;

    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate(){
        this.createdAt=LocalDateTime.now();
    }

    @OneToOne(mappedBy="patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Insurance insurance;

    @OneToMany(mappedBy="patient",cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Appointment> appointments =new ArrayList<>();

    @OneToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}