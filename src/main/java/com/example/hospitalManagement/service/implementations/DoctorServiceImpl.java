package com.example.hospitalManagement.service.implementations;

import com.example.hospitalManagement.dto.DoctorDto;
import com.example.hospitalManagement.dto.ScheduleDto;
import com.example.hospitalManagement.entity.Doctor;
import com.example.hospitalManagement.entity.DoctorSchedule;
import com.example.hospitalManagement.entity.Role;
import com.example.hospitalManagement.entity.User;
import com.example.hospitalManagement.exceptions.EntityNotFoundException;
import com.example.hospitalManagement.mapper.DoctorMapper;
import com.example.hospitalManagement.mapper.DoctorScheduleMapper;
import com.example.hospitalManagement.repository.DoctorRepository;
import com.example.hospitalManagement.repository.DoctorScheduleRepo;
import com.example.hospitalManagement.repository.UserRepository;
import com.example.hospitalManagement.service.services.DoctorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DoctorScheduleRepo scheduleRepo;
    public DoctorServiceImpl(DoctorRepository doctorRepository,
                             UserRepository userRepository,
                             PasswordEncoder passwordEncoder,
                             DoctorScheduleRepo scheduleRepo){
        this.doctorRepository=doctorRepository;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.scheduleRepo=scheduleRepo;
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
}
