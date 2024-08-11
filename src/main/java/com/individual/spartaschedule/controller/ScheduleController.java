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

    @GetMapping("/search/{id}")
    public Schedule scheduleFindById(@PathVariable int id){
        ScheduleService service = new ScheduleService(jdbcTemplate);
        return service.scheduleFindById(id);
    }

    @GetMapping("/search")
    public List<Schedule> scheduleFindByNameOrDate(@RequestParam(required = false) String name, @RequestParam(required = false) String modifyUpdate) {
        ScheduleService service = new ScheduleService(jdbcTemplate);
        return service.scheduleFindByNameOrDate(name, modifyUpdate);
    }

    @PutMapping("/modify/{id}")
    public Schedule scheduleModify(@PathVariable int id, ScheduleRequestDto requestDto){
        ScheduleService service = new ScheduleService(jdbcTemplate);
        return service.scheduleModify(id, requestDto);
    }

    @DeleteMapping("/delete")
    public void deleteScheduleByIdAndPassword(@RequestParam int id,@RequestParam String password){
        ScheduleService service = new ScheduleService(jdbcTemplate);
        service.deleteScheduleByIdAndPassword(id, password);
    }

}
