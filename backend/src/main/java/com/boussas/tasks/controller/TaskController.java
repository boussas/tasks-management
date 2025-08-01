package com.boussas.tasks.controller;

import com.boussas.tasks.mapper.TaskMapper;
import com.boussas.tasks.model.Task;
import com.boussas.tasks.model.User;
import com.boussas.tasks.model.dto.TaskDTO;
import com.boussas.tasks.repository.TaskListRepository;
import com.boussas.tasks.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final TaskListRepository taskListRepository;

    public TaskController(TaskService taskService, TaskMapper taskMapper, TaskListRepository taskListRepository) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
        this.taskListRepository = taskListRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable UUID id) {
        Optional<Task> taskOpt = taskService.findTaskById(id);
        return taskOpt.map(task -> ResponseEntity.ok(taskMapper.toDto(task)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/task-list/{task-list-id}")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO, @PathVariable("task-list-id") UUID taskListId) {
        return taskListRepository.findById(taskListId)
                .map(taskList -> {
                    Task task = taskMapper.fromDto(taskDTO);
                    task.setTaskList(taskList);

                    // Directly set string tags from DTO
                    if (taskDTO.getTags() != null) {
                        task.setTags(taskDTO.getTags());
                    } else {
                        task.setTags(Collections.emptySet());
                    }

                    if (taskDTO.getUserId() != null) {
                        task.setCreatedBy(User.builder().id(taskDTO.getUserId()).build());
                    }

                    Task createdTask = taskService.createTask(task);
                    return ResponseEntity.ok(taskMapper.toDto(createdTask));
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable UUID id, @RequestBody TaskDTO taskDTO) {
        Optional<Task> existingTaskOpt = taskService.findTaskById(id);
        if (existingTaskOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task existingTask = existingTaskOpt.get();

        if (taskDTO.getTitle() != null) {
            existingTask.setTitle(taskDTO.getTitle());
        }
        if (taskDTO.getDescription() != null) {
            existingTask.setDescription(taskDTO.getDescription());
        }
        if (taskDTO.getStatus() != null) {
            existingTask.setStatus(taskDTO.getStatus());
        }
        if (taskDTO.getPriority() != null) {
            existingTask.setPriority(taskDTO.getPriority());
        }
        if (taskDTO.getDueDate() != null) {
            existingTask.setDueDate(taskDTO.getDueDate());
        }
        if (taskDTO.getTaskListId() != null) {
            taskListRepository.findById(taskDTO.getTaskListId())
                    .ifPresent(existingTask::setTaskList);
        }
        if (taskDTO.getTags() != null) {
            existingTask.setTags(taskDTO.getTags());
        }
        if (taskDTO.getUserId() != null) {
            existingTask.setCreatedBy(User.builder().id(taskDTO.getUserId()).build());
        }

        existingTask.setUpdatedAt(java.time.LocalDateTime.now());

        Task updatedTask = taskService.updateTask(existingTask);
        return ResponseEntity.ok(taskMapper.toDto(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
