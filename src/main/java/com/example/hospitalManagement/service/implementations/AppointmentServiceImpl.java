package com.example.hospitalManagement.service.implementations;

import com.example.hospitalManagement.dto.AppointmentDto;
import com.example.hospitalManagement.entity.Appointment;
import com.example.hospitalManagement.entity.Doctor;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.exceptions.EntityNotFoundException;
import com.example.hospitalManagement.mapper.AppointmentMapper;
import com.example.hospitalManagement.repository.AppointmentRepository;
import com.example.hospitalManagement.repository.DoctorRepository;
import com.example.hospitalManagement.repository.PatientRepository;
import com.example.hospitalManagement.service.services.AppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  PatientRepository patientRepository,
                                  DoctorRepository doctorRepository){
        this.appointmentRepository=appointmentRepository;
        this.patientRepository=patientRepository;
        this.doctorRepository=doctorRepository;
    }

    @Override
    @Transactional
    public String createAppointment(AppointmentDto dto,Long patientId , Long doctorId){
        Appointment appointment= AppointmentMapper.toEntity(dto);
        Patient patient=patientRepository.findById(patientId).orElseThrow(
                ()-> new EntityNotFoundException("patient",patientId)
        );
        Doctor doctor= doctorRepository.findById(doctorId).orElseThrow(
                ()-> new EntityNotFoundException("Doctor",doctorId)
        );
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
        return "appointment Created";
    }
}
