package com.individual.spartaschedule.entity;

import com.individual.spartaschedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    /**
     SD_UNIQUE_NUMBER INT AUTO_INCREMENT PRIMARY KEY,                 고유번호
     SCHEDULE         VARCHAR(2000) NOT NULL,                         일정
     SD_NAME          VARCHAR(50)   NOT NULL,                         작성자
     SD_PASSWORD      VARCHAR(50)   NOT NULL,                         비밀번호
     SD_REGDATE       TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP,   작성일자
     SD_MODIFYDATE    TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP    수정일자

     */
    private int sd_unique_number;
    private String schedule;
    private String sd_name;

    private String sd_password;

    private Date sd_regDate;
    private Date sd_modifyDate;

    public Schedule(ScheduleRequestDto scheduleDto) {
        this.schedule = scheduleDto.getSchedule();
        this.sd_name = scheduleDto.getSd_name();
        this.sd_password = scheduleDto.getSd_password();

    }

    @Override
    public String toString() {
        return "Schedule{" +
                "sd_unique_number=" + sd_unique_number +
                ", schedule='" + schedule + '\'' +
                ", sd_name='" + sd_name + '\'' +
                ", sd_password='" + sd_password + '\'' +
                ", sd_regDate=" + sd_regDate +
                ", sd_modifyDate=" + sd_modifyDate +
                '}';
    }
}
