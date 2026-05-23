package ca.sheridancollege.thakumov.service;

import ca.sheridancollege.thakumov.model.Task;
import ca.sheridancollege.thakumov.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public void updateTask(Task task) {
        taskRepository.update(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public long countByStatus(String status) {
        return taskRepository.countByStatus(status);
    }
}