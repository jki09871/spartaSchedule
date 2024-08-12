package com.individual.spartaschedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    private String schedule;
    private String sd_name;
    private String sd_password;

    public ScheduleRequestDto() {}
    public ScheduleRequestDto(ScheduleRequestDto scheduleDto) {
        this.schedule = scheduleDto.getSchedule();
        this.sd_name = scheduleDto.getSd_name();
        this.sd_password = scheduleDto.getSd_password();
    }

}
