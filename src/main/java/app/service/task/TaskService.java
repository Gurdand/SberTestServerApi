package app.service.task;

import app.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    void createTask(Task task);

    List<Task> getAllTasks();

    Optional<Task> getTask(Long taskId);

    void updateTask(Long taskId, Task task);

    boolean isTaskExist(Long taskId);

}
