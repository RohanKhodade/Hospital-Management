package com.example.hospitalManagement.service.implementations;

import com.example.hospitalManagement.dto.MedicalRecordDto;
import com.example.hospitalManagement.entity.MedicalRecord;
import com.example.hospitalManagement.exceptions.EntityNotFoundException;
import com.example.hospitalManagement.mapper.MedicalRecordMapper;
import com.example.hospitalManagement.repository.MedicalRecordRepository;
import com.example.hospitalManagement.service.services.MedicalRecordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordImpl implements MedicalRecordService {
    private final MedicalRecordRepository recordRepository;
    public MedicalRecordImpl(MedicalRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public MedicalRecordDto getMedicalRecord(Long recordId) {
        MedicalRecord record=recordRepository.findById(recordId)
                .orElseThrow(()->new EntityNotFoundException("Medical Record",recordId));
        MedicalRecordDto dto=new MedicalRecordDto();
        return MedicalRecordMapper.toDto(record);
    }

    @Override
    public List<MedicalRecordDto> getAllPatientRecords(Long patientId){
        List<MedicalRecordDto> dtoList=new ArrayList<>();
        List<MedicalRecord> records=recordRepository.findAllByPatientId(patientId);
        for (MedicalRecord record: records){
            dtoList.add(MedicalRecordMapper.toDto(record));
        }
        return dtoList;
    }
    @Override
    public List<MedicalRecordDto> getAllDoctorRecords(Long doctorId){
        List<MedicalRecordDto> dtoList=new ArrayList<>();
        List<MedicalRecord> records=recordRepository.findAllByDoctorId(doctorId);
        for (MedicalRecord record: records){
            dtoList.add(MedicalRecordMapper.toDto(record));
        }
        return dtoList;
    }
}