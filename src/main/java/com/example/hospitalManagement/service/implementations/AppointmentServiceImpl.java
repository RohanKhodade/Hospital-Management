package com.example.hospitalManagement.service.implementations;

import com.example.hospitalManagement.dto.AppointmentDto;
import com.example.hospitalManagement.entity.*;
import com.example.hospitalManagement.exceptions.EntityNotFoundException;
import com.example.hospitalManagement.mapper.AppointmentMapper;
import com.example.hospitalManagement.repository.AppointmentRepository;
import com.example.hospitalManagement.repository.DoctorRepository;
import com.example.hospitalManagement.repository.DoctorScheduleRepo;
import com.example.hospitalManagement.repository.PatientRepository;
import com.example.hospitalManagement.service.services.AppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.EnumMap;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorScheduleRepo scheduleRepo;
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  PatientRepository patientRepository,
                                  DoctorRepository doctorRepository,
                                  DoctorScheduleRepo scheduleRepo){
        this.appointmentRepository=appointmentRepository;
        this.patientRepository=patientRepository;
        this.doctorRepository=doctorRepository;
        this.scheduleRepo=scheduleRepo;
    }

    @Override
    @Transactional
    public String createAppointment(AppointmentDto dto,Long patientId , Long doctorId){
        Patient patient=patientRepository.findById(patientId).orElseThrow(
                ()-> new EntityNotFoundException("patient",patientId)
        );
        Doctor doctor= doctorRepository.findById(doctorId).orElseThrow(
                ()-> new EntityNotFoundException("Doctor",doctorId)
        );
        // extract the date time and day of week requested
        LocalDateTime requestedDateTime=LocalDateTime.parse(dto.getTime());
        LocalTime requestedTime=requestedDateTime.toLocalTime();
        DayOfWeek requestedDay=requestedDateTime.getDayOfWeek();

        // check whether doctor is available at the requested day date and time;
        DoctorSchedule schedule=scheduleRepo.findByDoctorIdAndDayOfWeek(doctorId,requestedDay)
                .orElseThrow(()->new RuntimeException("Doctor not available at requested day"));
        // check requested time is within working hrs
        if (requestedTime.isBefore(schedule.getStartTime())||
                requestedTime.isAfter(schedule
                        .getEndTime().minusMinutes(schedule.getSlotDurationMinutes()))){
            throw new RuntimeException("time is outside doctors working hours");

        }
        // check no double booking at exact slot
        boolean alreadyBooked=appointmentRepository
                .existsByDoctorIdAndTime(doctorId,requestedDateTime);
        if (alreadyBooked){
            throw new RuntimeException("Another Appointment exists at the requested time");
        }
        // check if the slot chosen is valid
        long minutesFromStart= ChronoUnit.MINUTES.between(schedule.getStartTime(),requestedTime);
        if (minutesFromStart%schedule.getSlotDurationMinutes()!=0){
            throw new RuntimeException(" please choose the valid slot!"+
                    " for example schedule start time is : "+ schedule.getStartTime()+
                    " next slot is after : "+ schedule.getSlotDurationMinutes()+" minutes");
        }
        Appointment appointment= AppointmentMapper.toEntity(dto);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentStatus(AppointmentStatus.SCHEDULED);
        appointmentRepository.save(appointment);
        return "appointment Created";
    }
}