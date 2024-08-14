package com.individual.spartaschedule.repository;

import com.individual.spartaschedule.dto.ScheduleRequestDto;
import com.individual.spartaschedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

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

    public Schedule scheduleFindById(int id) {
        // DB 조회
        String sql = "SELECT * FROM PERSONALSCHEDULE_TBL WHERE SD_UNIQUE_NUMBER = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setSchedule(resultSet.getString("schedule"));
                schedule.setSd_name(resultSet.getString("sd_name"));
                schedule.setSd_password(resultSet.getString("sd_password"));
                schedule.setSd_regDate(resultSet.getDate("sd_regDate"));
                schedule.setSd_modifyDate(resultSet.getDate("sd_modifyDate"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }

    public List<Schedule> scheduleFindByNameOrDate(String name, String modifyUpdate) {
        // 빈 문자열을 null로 변환
        if (name != null && name.trim().isEmpty()) {
            name = null;
        }
        if (modifyUpdate != null && modifyUpdate.trim().isEmpty()) {
            modifyUpdate = null;
        }

        // SQL 쿼리 작성
        String sql = "SELECT * FROM PERSONALSCHEDULE_TBL " +
                "WHERE (DATE(SD_MODIFYDATE) = ? OR ? IS NULL) " +
                "AND (SD_NAME = ? OR ? IS NULL) " +
                "ORDER BY SD_MODIFYDATE DESC";

        // 쿼리 실행
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            // ResultSet에서 Schedule 객체로 매핑
            Schedule schedule = new Schedule();
            schedule.setSchedule(rs.getString("schedule"));
            schedule.setSd_name(rs.getString("sd_name"));
            schedule.setSd_password(rs.getString("sd_password"));
            schedule.setSd_regDate(rs.getDate("sd_regDate"));
            schedule.setSd_modifyDate(rs.getDate("sd_modifyDate"));
            return schedule;
        }, modifyUpdate, modifyUpdate, name, name);
    }

    public Schedule scheduleModify(int id, ScheduleRequestDto requestDto) {
        String sql = "UPDATE PERSONALSCHEDULE_TBL\n" +
                "SET\n" +
                "    SCHEDULE = ?,\n" +
                "    SD_NAME = ?,\n" +
                "    SD_MODIFYDATE = NOW()\n" +
                "WHERE\n" +
                "    SD_UNIQUE_NUMBER = ?\n" +
                "    AND SD_PASSWORD = ?";
        jdbcTemplate.update(sql, requestDto.getSchedule(), requestDto.getSd_name(), id, requestDto.getSd_password());
        return scheduleFindById(id);
    }

    public void deleteScheduleByIdAndPassword(int id, String password) {
        String sql = "DELETE FROM\n" +
                "    PERSONALSCHEDULE_TBL\n" +
                "WHERE\n" +
                "    SD_UNIQUE_NUMBER = ?\n" +
                "    AND SD_PASSWORD = ?";
        jdbcTemplate.update(sql, id, password);
    }
}