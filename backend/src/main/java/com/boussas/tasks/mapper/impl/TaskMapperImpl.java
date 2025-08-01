package com.boussas.tasks.mapper.impl;

import com.boussas.tasks.mapper.TaskMapper;
import com.boussas.tasks.model.Task;
import com.boussas.tasks.model.User;
import com.boussas.tasks.model.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDTO toDto(Task task) {
        if (task == null) return null;
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .taskListId(task.getTaskList() != null ? task.getTaskList().getId() : null)
                .tags(task.getTags())
                .userId(task.getCreatedBy() != null ? task.getCreatedBy().getId() : null)
                .build();
    }

    @Override
    public Task fromDto(TaskDTO taskDTO) {
        if (taskDTO == null) return null;

        return Task.builder()
                .id(taskDTO.getId())
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .status(taskDTO.getStatus())
                .priority(taskDTO.getPriority())
                .dueDate(taskDTO.getDueDate())
                .createdAt(taskDTO.getCreatedAt())
                .updatedAt(taskDTO.getUpdatedAt())
                .tags(taskDTO.getTags() != null ? new HashSet<>(taskDTO.getTags()) : new HashSet<>())
                .createdBy(taskDTO.getUserId() != null ? User.builder().id(taskDTO.getUserId()).build() : null)
                .build();
    }

}
