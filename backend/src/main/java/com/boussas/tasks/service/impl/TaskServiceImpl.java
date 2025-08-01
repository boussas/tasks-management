package com.boussas.tasks.service.impl;

import com.boussas.tasks.model.Task;
import com.boussas.tasks.model.User;
import com.boussas.tasks.repository.TaskRepository;
import com.boussas.tasks.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> findTaskById(UUID id) {
        return taskRepository.findById(id);
    }

    @Override
    public void deleteTaskById(UUID id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findTasksByUser(User user) {
        return taskRepository.findByCreatedBy(user);
    }
}
