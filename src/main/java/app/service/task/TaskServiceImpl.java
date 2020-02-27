package app.service.task;

import app.dao.TaskRepository;
import app.dto.TaskDto;
import app.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void createTask(Task task) {
        taskRepository.saveTask(task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.getAllTasks().stream().map(this::taskToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<TaskDto> getTask(Long taskId) {
        return taskRepository.getTaskById(taskId).map(this::taskToDto);
    }

    @Override
    public void updateTask(Long taskId, Task task) {
        task.setId(taskId);
        taskRepository.updateTask(task);
    }

    @Override
    public boolean isTaskExist(Long taskId) {
        return taskRepository.getTaskById(taskId).isPresent();
    }

    @Override
    public TaskDto taskToDto(Task task) {
        return new TaskDto(task.getId(), task.getQuestion(), task.getAnswerOptions());
    }
}
