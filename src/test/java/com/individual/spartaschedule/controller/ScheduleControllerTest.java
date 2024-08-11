package com.individual.spartaschedule.controller;

import com.individual.spartaschedule.dto.ScheduleRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class ScheduleControllerTest {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    ScheduleControllerTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Test
    @DisplayName("스케줄 작성")
    void contextLoads() {
        ScheduleRequestDto dto = new ScheduleRequestDto();
        dto.setSchedule("일정");
        dto.setSd_name("홍길동");
        dto.setSd_password("a123");
        ScheduleController controller = new ScheduleController(jdbcTemplate);
        controller.createSchedule(dto);
    }
}