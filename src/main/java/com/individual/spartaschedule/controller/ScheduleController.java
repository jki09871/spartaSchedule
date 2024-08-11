package com.individual.spartaschedule.controller;

import com.individual.spartaschedule.dto.ScheduleRequestDto;
import com.individual.spartaschedule.dto.ScheduleResponseDto;
import com.individual.spartaschedule.entity.Schedule;
import com.individual.spartaschedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/creates")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleDto){
        ScheduleService service = new ScheduleService(jdbcTemplate);

        return service.createSchedule(scheduleDto);
    }



}
