package app.service.answer;

import app.dto.AnswerDto;
import app.model.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

    Optional<AnswerDto> checkUserAnswer(String login, long taskId, String answer);

    List<AnswerDto> getUserAnswer(String login);

    AnswerDto answerToDto(Answer answer);

    boolean answerIsExist(Long userId, Long taskId);
}
