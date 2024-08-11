package com.individual.spartaschedule.dto;

import com.individual.spartaschedule.entity.Schedule;
import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleResponseDto {
    private int sd_unique_number;
    private String schedule;
    private String sd_name;
    private String sd_password;
    private Date sd_regDate;
    private Date sd_modifyDate;



    public ScheduleResponseDto(Schedule scheduleDto) {
        this.schedule = scheduleDto.getSchedule();
        this.sd_name = scheduleDto.getSd_name();
        this.sd_password = scheduleDto.getSd_password();
    }
}
