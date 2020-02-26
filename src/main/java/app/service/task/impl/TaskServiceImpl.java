package app.service.task.impl;

import app.dao.TaskRepository;
import app.model.Task;
import app.service.task.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    @Override
    public Optional<Task> getTask(Long taskId) {
        return taskRepository.getTaskById(taskId);
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
}
