package app.service.task;

public interface AnswerService {

    boolean checkUserAnswer(String login, long taskId, String answer);
}
