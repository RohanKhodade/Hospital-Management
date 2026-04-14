package com.example.hospitalManagement.repository;

import com.example.hospitalManagement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance,Long> {
    Optional<Insurance> findByPatientId(Long patientId);
}
