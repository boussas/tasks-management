package com.boussas.tasks.service.impl;

import com.boussas.tasks.model.TaskList;
import com.boussas.tasks.model.User;
import com.boussas.tasks.repository.TaskListRepository;
import com.boussas.tasks.service.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository repository;

    public TaskListServiceImpl(TaskListRepository repository) {
        this.repository = repository;
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (taskList.getCreatedAt() == null) {
            taskList.setCreatedAt(LocalDateTime.now());
        }
        taskList.setUpdatedAt(LocalDateTime.now());
        return repository.save(taskList);
    }

    @Override
    public Optional<TaskList> getTaskListById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public TaskList updateTaskList(TaskList taskList) {
        Optional<TaskList> existingOpt = repository.findById(taskList.getId());
        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("TaskList not found with id: " + taskList.getId());
        }
        TaskList existing = existingOpt.get();
        existing.setTitle(taskList.getTitle());
        existing.setDescription(taskList.getDescription());
        existing.setUpdatedAt(LocalDateTime.now());
        if (taskList.getOwner() != null) {
            existing.setOwner(taskList.getOwner());
        }

        return repository.save(existing);
    }

    @Override
    public void deleteTaskList(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<TaskList> getTaskListsByUser(User user) {
        return repository.findByOwner(user);
    }
}
