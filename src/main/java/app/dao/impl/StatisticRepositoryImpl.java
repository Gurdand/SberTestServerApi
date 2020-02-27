package app.dao.impl;

import app.dao.StatisticRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticRepositoryImpl implements StatisticRepository {

    private JdbcTemplate jdbcTemplate;

    public StatisticRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer userCount() {
        try {
            return jdbcTemplate.queryForObject("SELECT COUNT(id) FROM users", Integer.class);
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public Integer taskCount() {
        try {
            return jdbcTemplate.queryForObject("SELECT COUNT(id) FROM tasks", Integer.class);
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public Integer UserCountHaveAnswer() {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT COUNT(user_id) FROM (select user_id from answers group by user_id)", Integer.class);
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public Integer UserCountHaveAllAnswer(int taskCount) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT COUNT(user_id) FROM (select user_id from answers group by user_id HAVING COUNT(task_id) = ?)",
                    new Object[] {taskCount}, Integer.class);
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public Integer UserCountHaveAllAnswerIsPass(int taskCount) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT COUNT(user_id) FROM (SELECT user_id FROM answers WHERE is_passed = 1 GROUP BY user_id HAVING COUNT(task_id) = ?)",
                    new Object[] {taskCount}, Integer.class);
        } catch (DataAccessException e) {
            return 0;
        }
    }
}
