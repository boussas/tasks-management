package com.boussas.tasks.mappers.impl;

import com.boussas.tasks.mappers.TaskListMapper;
import com.boussas.tasks.mappers.TaskMapper;
import com.boussas.tasks.mappers.UserMapper;
import com.boussas.tasks.model.TaskList;
import com.boussas.tasks.model.dto.TaskListDTO;

import java.util.stream.Collectors;

public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;
    private final UserMapper userMapper;

    public TaskListMapperImpl(TaskMapper taskMapper, UserMapper userMapper) {
        this.taskMapper = taskMapper;
        this.userMapper = userMapper;
    }

    @Override
    public TaskListDTO toDto(TaskList taskList) {
        if (taskList == null) return null;

        return TaskListDTO.builder()
                .id(taskList.getId())
                .title(taskList.getTitle())
                .description(taskList.getDescription())
                .createdAt(taskList.getCreatedAt())
                .updatedAt(taskList.getUpdatedAt())
                .owner(userMapper.toDto(taskList.getOwner()))
                .tasks(taskList.getTasks() != null
                        ? taskList.getTasks().stream().map(taskMapper::toDto).collect(Collectors.toList())
                        : null)
                .build();
    }

    @Override
    public TaskList fromDto(TaskListDTO dto) {
        if (dto == null) return null;

        return TaskList.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .owner(userMapper.fromDto(dto.getOwner()))
                .tasks(dto.getTasks() != null
                        ? dto.getTasks().stream().map(taskMapper::fromDto).collect(Collectors.toList())
                        : null)
                .build();
    }
}
