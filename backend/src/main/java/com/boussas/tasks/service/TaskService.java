package com.boussas.tasks.service;

import com.boussas.tasks.model.Task;
import com.boussas.tasks.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    Optional<Task> findTaskById(UUID id);

    void deleteTaskById(UUID id);

    Task createTask(Task task);

    Task updateTask(Task task);

    List<Task> findTasksByUser(User user);
}
