package app.service.task;

import app.dto.TaskDto;
import app.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    void createTask(Task task);

    List<TaskDto> getAllTasks();

    Optional<TaskDto> getTask(Long taskId);

    void updateTask(Long taskId, Task task);

    boolean isTaskExist(Long taskId);

    TaskDto taskToDto(Task task);

}
