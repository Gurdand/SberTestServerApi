package app.dao;

import app.model.Answer;

public interface AnswerRepository {

    void saveResult(Answer userAnswer);
}
