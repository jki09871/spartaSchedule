package com.individual.spartaschedule.service;

import com.individual.spartaschedule.dto.ScheduleRequestDto;
import com.individual.spartaschedule.dto.ScheduleResponseDto;
import com.individual.spartaschedule.entity.Schedule;
import com.individual.spartaschedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ScheduleService {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleDto) {

        Schedule dto = new Schedule(scheduleDto);
        ScheduleRepository repository = new ScheduleRepository(jdbcTemplate);
        repository.scheduleSave(dto);
        ScheduleResponseDto responseDto = new ScheduleResponseDto(dto);

        return responseDto;

    }

    public Schedule ScheduleFindById(int id) {
        ScheduleRepository repository = new ScheduleRepository(jdbcTemplate);
        return repository.ScheduleFindById(id);
    }

    public List<Schedule> ScheduleFindByNameOrDate(String name, String modifyUpdate) {
        ScheduleRepository repository = new ScheduleRepository(jdbcTemplate);
        return repository.ScheduleFindByNameOrDate(name, modifyUpdate);
    }
}
