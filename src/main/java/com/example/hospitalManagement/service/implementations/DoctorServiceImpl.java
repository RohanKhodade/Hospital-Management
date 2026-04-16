package com.example.hospitalManagement.service.implementations;

import com.example.hospitalManagement.dto.DoctorDto;
import com.example.hospitalManagement.dto.ScheduleDto;
import com.example.hospitalManagement.entity.*;
import com.example.hospitalManagement.exceptions.EntityNotFoundException;
import com.example.hospitalManagement.mapper.DoctorMapper;
import com.example.hospitalManagement.mapper.DoctorScheduleMapper;
import com.example.hospitalManagement.repository.*;
import com.example.hospitalManagement.service.services.DoctorService;
import com.example.hospitalManagement.util.AuthUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DoctorScheduleRepo scheduleRepo;
    private final AuthUtil authUtil;
    private final MedicalRecordRepository medicalRecordRepository;
    private final AppointmentRepository appointmentRepository;
    public DoctorServiceImpl(DoctorRepository doctorRepository,
                             UserRepository userRepository,
                             PasswordEncoder passwordEncoder,
                             DoctorScheduleRepo scheduleRepo,
                             AuthUtil authUtil,
                             AppointmentRepository appointmentRepository,
                             MedicalRecordRepository medicalRecordRepository){
        this.doctorRepository=doctorRepository;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.scheduleRepo=scheduleRepo;
        this.authUtil=authUtil;
        this.appointmentRepository=appointmentRepository;
        this.medicalRecordRepository=medicalRecordRepository;
    }
    @Override
    public String createDoctor(DoctorDto dto){

        User user=new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.ROLE_DOCTOR);
        userRepository.save(user);

        Doctor doctor=new Doctor();
        doctor.setName(dto.getName());
        doctor.setUser(user);
        doctorRepository.save(doctor);
        return "Doctor saved Successfully";
    }
    @Override
    public String deleteDoctor(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                ()-> new EntityNotFoundException("doctor", doctorId)
        );
        doctorRepository.deleteById(doctorId);
        return "doctor with Id : " + doctorId +" Deleted ";
    }

    @Override
    public DoctorDto getAppointments(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                ()-> new EntityNotFoundException("doctor", doctorId)
        );
        authUtil.checkAccess(doctor.getUser().getUsername());
        return DoctorMapper.toDto(doctor);
    }

    @Override
    public String createSchedule(ScheduleDto dto, Long doctor_id){
        Doctor doctor=doctorRepository.findById(doctor_id)
                .orElseThrow(()-> new EntityNotFoundException("Doctor",doctor_id));
        DoctorSchedule schedule= DoctorScheduleMapper.toEntity(dto);
        schedule.setDoctor(doctor);
        scheduleRepo.save(schedule);
        return "schedule created";
    }

    @Override
    public String completeAppointment(Long appointmentId,
                                      String prescription,
                                      String notes){
        Appointment appointment=appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new EntityNotFoundException("Appointment", appointmentId));
        appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);
        appointmentRepository.save(appointment);

        MedicalRecord medicalRecord=new MedicalRecord();
        medicalRecord.setDoctor(appointment.getDoctor());
        medicalRecord.setPatient(appointment.getPatient());
        medicalRecord.setDiagnosis(appointment.getReason());
        medicalRecord.setPrescription(prescription);
        medicalRecord.setNote(notes);
        medicalRecordRepository.save(medicalRecord);
        return "appointment completed ";
    }
}
