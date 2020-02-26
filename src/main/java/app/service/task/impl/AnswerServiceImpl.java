package app.service.task.impl;

import app.dao.AnswerRepository;
import app.dao.TaskRepository;
import app.dao.UserRepository;
import app.model.Answer;
import app.model.Task;
import app.model.User;
import app.service.task.AnswerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
    public boolean checkUserAnswer(String login, long taskId, String answer) {
        Optional<User> user = userRepository.findUserByLogin(login);
        Optional<Task> task = taskRepository.getTaskById(taskId);

        if (user.isPresent() && task.isPresent()) {
            if (task.get().getCorrectAnswer() == null) {
                answerRepository.saveResult(new Answer(user.get().getId(), taskId, answer, false));
                return true;
            }

            boolean isPass = task.get().getCorrectAnswer().equals(answer);

            answerRepository.saveResult(
                    new Answer(user.get().getId(), taskId, answer, isPass));

            return isPass;
        }

        return false;
    }
}
