package com.boussas.tasks.mappers.impl;

import com.boussas.tasks.mappers.TagMapper;
import com.boussas.tasks.mappers.TaskListMapper;
import com.boussas.tasks.mappers.TaskMapper;
import com.boussas.tasks.mappers.UserMapper;
import com.boussas.tasks.model.Task;
import com.boussas.tasks.model.dto.TaskDTO;

import java.util.stream.Collectors;

public class TaskMapperImpl implements TaskMapper {

    private final TaskListMapper taskListMapper;
    private final TagMapper tagMapper;
    private final UserMapper userMapper;

    public TaskMapperImpl(TaskListMapper taskListMapper, TagMapper tagMapper, UserMapper userMapper) {
        this.taskListMapper = taskListMapper;
        this.tagMapper = tagMapper;
        this.userMapper = userMapper;
    }

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
                .taskList(taskListMapper.toDto(task.getTaskList()))
                .tags(task.getTags() != null
                        ? task.getTags().stream().map(tagMapper::toDto).collect(Collectors.toList())
                        : null)
                .createdBy(userMapper.toDto(task.getCreatedBy()))
                .build();
    }

    @Override
    public Task fromDto(TaskDTO dto) {
        if (dto == null) return null;

        return Task.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .dueDate(dto.getDueDate())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .taskList(taskListMapper.fromDto(dto.getTaskList()))
                .tags(dto.getTags() != null
                        ? dto.getTags().stream().map(tagMapper::fromDto).collect(Collectors.toList())
                        : null)
                .createdBy(userMapper.fromDto(dto.getCreatedBy()))
                .build();
    }
}
