package com.example.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy="doctor",orphanRemoval = true)
    private List<Appointment> appointments= new ArrayList<>();

    @OneToOne(mappedBy="headDoctor")
    private Department department;

    @ManyToMany(mappedBy="doctors")
    private List<Department> departments=new ArrayList<>();

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
}
