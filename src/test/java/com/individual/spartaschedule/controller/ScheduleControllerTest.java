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
        Schedule schedule = service.ScheduleFindById(1);
        System.out.println("schedule = " + schedule);
    }

    @Test
    @DisplayName("스케줄 상세정보")
    void ScheduleFindByNameOrDate() {
        ScheduleService service = new ScheduleService(jdbcTemplate);
        List<Schedule> 홍길동 = service.ScheduleFindByNameOrDate("", "");
        System.out.println("schedule = " + 홍길동);

    }
}