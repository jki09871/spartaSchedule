package com.individual.spartaschedule.controller;

import com.individual.spartaschedule.dto.ScheduleRequestDto;
import com.individual.spartaschedule.dto.ScheduleResponseDto;
import com.individual.spartaschedule.entity.Schedule;
import com.individual.spartaschedule.service.ScheduleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest
class ScheduleControllerTest {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    ScheduleControllerTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Test
    @DisplayName("스케줄 작성")
    void createSchedule() {
        ScheduleRequestDto dto = new ScheduleRequestDto();
        dto.setSchedule("일정");
        dto.setSd_name("홍길동");
        dto.setSd_password("a123");
        ScheduleController controller = new ScheduleController(jdbcTemplate);
        controller.createSchedule(dto);
    }

    @Test
    @DisplayName("스케줄 상세정보")
    void scheduleFindById() {
        ScheduleService service = new ScheduleService(jdbcTemplate);
        Schedule schedule = service.scheduleFindById(1);
        System.out.println("schedule = " + schedule);
    }

    @Test
    @DisplayName("스케줄 다중 상세정보")
    void scheduleFindByNameOrDate() {
        ScheduleService service = new ScheduleService(jdbcTemplate);
        List<Schedule> nameOrDateNull = service.scheduleFindByNameOrDate("", "");
        System.out.println("nameOrDateNull = " + nameOrDateNull);
        List<Schedule> scheduleFindByName = service.scheduleFindByNameOrDate("김아무개", "");
        System.out.println("scheduleFindByName = " + scheduleFindByName);
        List<Schedule> scheduleFindByDate = service.scheduleFindByNameOrDate("", "2024-08-10");
        System.out.println("scheduleFindByDate = " + scheduleFindByDate);

    }

    @Test
    @DisplayName("스케줄 변경")
    void scheduleModify() {
        ScheduleService service = new ScheduleService(jdbcTemplate);
        ScheduleRequestDto schedule = new ScheduleRequestDto();
        schedule.setSchedule("일정 변경");
        schedule.setSd_name("이름도 변경");
        schedule.setSd_password("1234");
        Schedule schedules = service.scheduleModify(1, schedule);
        System.out.println("schedules = " + schedules);

    }
}