package app.service.answer;

import app.dao.AnswerRepository;
import app.dao.TaskRepository;
import app.dao.UserRepository;
import app.dto.AnswerDto;
import app.model.Answer;
import app.model.Task;
import app.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private AnswerRepository answerRepository;

    public AnswerServiceImpl(UserRepository userRepository,
                             TaskRepository taskRepository,
                             AnswerRepository answerRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public Optional<AnswerDto> checkUserAnswer(String login, long taskId, String answer) {
        Optional<User> user = userRepository.findUserByLogin(login);
        Optional<Task> task = taskRepository.getTaskById(taskId);

        if (user.isPresent() && task.isPresent() && answer != null) {

            Answer userAnswer = new Answer(user.get().getId(), taskId, answer, false);

            if (task.get().getCorrectAnswer() == null) {
                userAnswer.setPassed(true);

            } else {
                userAnswer.setPassed(task.get().getCorrectAnswer().equals(answer));
            }

            if (answerIsExist(user.get().getId(), task.get().getId())) {
                answerRepository.updateAnswer(userAnswer);
            } else {
                answerRepository.saveAnswer(userAnswer);
            }

            return Optional.of(answerToDto(userAnswer));
        }

        return Optional.empty();
    }

    @Override
    public List<AnswerDto> getUserAnswer(String login) {
        Optional<User> userOptional = userRepository.findUserByLogin(login);
        return userOptional.map(user -> answerRepository.getUserAnswer(user.getId()).stream()
                .map(this::answerToDto).collect(Collectors.toList())).orElseGet(ArrayList::new);
    }

    @Override
    public AnswerDto answerToDto(Answer answer) {
        return new AnswerDto(answer.getTaskId(), answer.getAnswer(), answer.isPassed());
    }

    @Override
    public boolean answerIsExist(Long userId, Long taskId) {
        return answerRepository.getAnswerByUserIdAndTaskId(userId,taskId).isPresent();
    }
}
