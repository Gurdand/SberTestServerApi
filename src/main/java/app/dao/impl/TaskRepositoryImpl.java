package app.dao.impl;

import app.dao.TaskRepository;
import app.model.Task;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Task> TASK_ROW_MAPPER = (rs, rowNum) -> new Task(
            rs.getLong("id"),
            rs.getString("question"),
            new ArrayList<String>() {{
                add(rs.getString("option_1"));
                add(rs.getString("option_2"));
                add(rs.getString("option_3"));
                add(rs.getString("option_4"));
            }},
            rs.getString("answer"));

    public TaskRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveTask(Task task) {
        jdbcTemplate.update(
                "INSERT INTO tasks " +
                        "(question, option_1, option_2, option_3, option_4, answer) " +
                        "VALUES (?,?,?,?,?,?)",
                    task.getQuestion(),
                    task.getAnswerOptions().get(0),
                    task.getAnswerOptions().get(1),
                    task.getAnswerOptions().get(2),
                    task.getAnswerOptions().get(3),
                    task.getCorrectAnswer()
        );
    }

    @Override
    public List<Task> getAllTasks() {
        return jdbcTemplate.query("SELECT * FROM tasks", TASK_ROW_MAPPER);
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        try {
            return Optional.of(
                    jdbcTemplate.queryForObject("SELECT * FROM tasks WHERE id = ?",
                    new Object[] {id},
                    TASK_ROW_MAPPER));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateTask(Task task) {
        jdbcTemplate.update(
                "UPDATE tasks SET " +
                        "question = ?, option_1 = ?, option_2 = ?, option_3 = ?, option_4 = ?, answer = ? " +
                        "WHERE id = ?",
                        task.getQuestion(),
                        task.getAnswerOptions().get(0),
                        task.getAnswerOptions().get(1),
                        task.getAnswerOptions().get(2),
                        task.getAnswerOptions().get(3),
                        task.getCorrectAnswer(),
                        task.getId()
                );
    }
}
