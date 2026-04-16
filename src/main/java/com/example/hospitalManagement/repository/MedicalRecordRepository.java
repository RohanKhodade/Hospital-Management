package com.example.hospitalManagement.repository;

import com.example.hospitalManagement.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {
    List<MedicalRecord> findAllByPatientId(Long patientId);
    List<MedicalRecord> findAllByDoctorId(Long doctorId);

}
