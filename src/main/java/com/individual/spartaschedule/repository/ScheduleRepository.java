package com.individual.spartaschedule.repository;

import com.individual.spartaschedule.dto.ScheduleRequestDto;
import com.individual.spartaschedule.dto.ScheduleResponseDto;
import com.individual.spartaschedule.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule scheduleSave(Schedule dto) {

        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO PERSONALSCHEDULE_TBL (schedule, sd_name, sd_password) VALUES (?, ?, ?)";

        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, dto.getSchedule());
                    preparedStatement.setString(2, dto.getSd_name());
                    preparedStatement.setString(3, dto.getSd_password()); // 인덱스 3으로 수정
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        int id = (int) keyHolder.getKey().longValue();
        dto.setSd_unique_number(id);

        return dto;
    }

    public Schedule ScheduleFindById(int id) {
        // DB 조회
        String sql = "SELECT * FROM PERSONALSCHEDULE_TBL WHERE sd_unique_number = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setSchedule(resultSet.getString("schedule"));
                schedule.setSd_name(resultSet.getString("sd_name"));
                schedule.setSd_regDate(resultSet.getDate("sd_regDate"));
                schedule.setSd_modifyDate(resultSet.getDate("sd_modifyDate"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }
}
