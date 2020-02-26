package app.dao.impl;

import app.dao.AnswerRepository;
import app.model.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

    private JdbcTemplate jdbcTemplate;

    public AnswerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveResult(Answer answer) {

    }

}
