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

    public Schedule scheduleFindById(int id) {
        ScheduleRepository repository = new ScheduleRepository(jdbcTemplate);
        return repository.scheduleFindById(id);
    }

    public List<Schedule> scheduleFindByNameOrDate(String name, String modifyUpdate) {
        ScheduleRepository repository = new ScheduleRepository(jdbcTemplate);
        return repository.scheduleFindByNameOrDate(name, modifyUpdate);
    }

    public Schedule scheduleModify(int id, ScheduleRequestDto requestDto) {
        ScheduleRepository repository = new ScheduleRepository(jdbcTemplate);
        Schedule schedule = repository.scheduleFindById(id);
        if (schedule != null){
            return repository.scheduleModify(id, requestDto);
        } else {
            throw new NullPointerException("해당하는 일정이 없습니다.");
        }
    }

    public void deleteScheduleByIdAndPassword(int id, String password) {
        ScheduleRepository repository = new ScheduleRepository(jdbcTemplate);
        Schedule schedule = repository.scheduleFindById(id);
        if (schedule != null){
            repository.deleteScheduleByIdAndPassword(id, password);
        } else {
            throw new NullPointerException("해당하는 일정이 없습니다.");
        }
    }
}
