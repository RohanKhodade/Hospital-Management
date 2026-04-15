package com.example.hospitalManagement.mapper;

import com.example.hospitalManagement.dto.ScheduleDto;
import com.example.hospitalManagement.entity.DoctorSchedule;
import org.springframework.stereotype.Component;

@Component
public class DoctorScheduleMapper {

    public static DoctorSchedule toEntity(ScheduleDto dto) {
        DoctorSchedule schedule = new DoctorSchedule();
        schedule.setDayOfWeek(dto.getDayOfWeek());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        schedule.setSlotDurationMinutes(dto.getSlotDuration());
        return schedule;
    }
}