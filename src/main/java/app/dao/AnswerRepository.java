package app.dao;

import app.model.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository {

    List<Answer> getUserAnswer(Long userId);

    Optional<Answer> getAnswerByUserIdAndTaskId(Long userId, Long taskId);

    void saveAnswer(Answer userAnswer);

    void updateAnswer(Answer answer);

}
