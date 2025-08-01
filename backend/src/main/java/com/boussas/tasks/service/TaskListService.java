package com.boussas.tasks.service;

import com.boussas.tasks.model.TaskList;
import com.boussas.tasks.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskListById(UUID id);
    TaskList updateTaskList(TaskList taskList);
    void deleteTaskList(UUID id);
    List<TaskList> getTaskListsByUser(User user);
}
