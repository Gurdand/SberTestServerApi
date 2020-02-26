package app.dao;

import app.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    void saveTask(Task task);

    List<Task> getAllTasks();

    Optional<Task> getTaskById(Long taskId);

    void updateTask(Task task);
}
