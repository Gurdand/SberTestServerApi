package app.dao.impl;

import app.dao.AnswerRepository;
import app.model.Answer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Answer> ANSWER_ROW_MAPPER = (rs, rowNum) -> new Answer(
            rs.getLong("user_id"),
            rs.getLong("task_id"),
            rs.getString("answer"),
            rs.getBoolean("is_passed")
            );

    public AnswerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Answer> getUserAnswer(Long userId) {
        try {
            return jdbcTemplate.query("SELECT * FROM answers WHERE user_id = ?",
                    new Object[] {userId}, ANSWER_ROW_MAPPER);
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Answer> getAnswerByUserIdAndTaskId(Long userId, Long taskId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    "SELECT * FROM answers WHERE user_id = ? AND task_id = ?",
                    new Object[] {userId,taskId},
                    ANSWER_ROW_MAPPER));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void saveAnswer(Answer answer) {
        jdbcTemplate.update("INSERT INTO answers VALUES (?,?,?,?)",
                answer.getUserId(),
                answer.getTaskId(),
                answer.getAnswer(),
                answer.isPassed()
        );
    }

    @Override
    public void updateAnswer(Answer answer) {
        jdbcTemplate.update("UPDATE answers SET user_id = ?, task_id = ?, answer = ?, is_passed = ?",
                answer.getUserId(),
                answer.getTaskId(),
                answer.getAnswer(),
                answer.isPassed());
    }

}
