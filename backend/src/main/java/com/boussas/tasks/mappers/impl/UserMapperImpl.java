package com.boussas.tasks.mappers.impl;

import com.boussas.tasks.mappers.TagMapper;
import com.boussas.tasks.mappers.TaskListMapper;
import com.boussas.tasks.mappers.TaskMapper;
import com.boussas.tasks.mappers.UserMapper;
import com.boussas.tasks.model.User;
import com.boussas.tasks.model.dto.UserDTO;

import java.util.stream.Collectors;

public class UserMapperImpl implements UserMapper {

    private final TaskListMapper taskListMapper;
    private final TaskMapper taskMapper;
    private final TagMapper tagMapper;

    public UserMapperImpl(TaskListMapper taskListMapper, TaskMapper taskMapper, TagMapper tagMapper) {
        this.taskListMapper = taskListMapper;
        this.taskMapper = taskMapper;
        this.tagMapper = tagMapper;
    }

    @Override
    public UserDTO toDto(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .taskLists(user.getTaskLists() != null
                        ? user.getTaskLists().stream().map(taskListMapper::toDto).collect(Collectors.toList())
                        : null)
                .tags(user.getTags() != null
                        ? user.getTags().stream().map(tagMapper::toDto).collect(Collectors.toList())
                        : null)
                .tasks(user.getTasks() != null
                        ? user.getTasks().stream().map(taskMapper::toDto).collect(Collectors.toList())
                        : null)
                .build();
    }

    @Override
    public User fromDto(UserDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .createdAt(dto.getCreatedAt())
                .taskLists(dto.getTaskLists() != null
                        ? dto.getTaskLists().stream().map(taskListMapper::fromDto).collect(Collectors.toList())
                        : null)
                .tags(dto.getTags() != null
                        ? dto.getTags().stream().map(tagMapper::fromDto).collect(Collectors.toList())
                        : null)
                .tasks(dto.getTasks() != null
                        ? dto.getTasks().stream().map(taskMapper::fromDto).collect(Collectors.toList())
                        : null)
                .build();
    }
}
