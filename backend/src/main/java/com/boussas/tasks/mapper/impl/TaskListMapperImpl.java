package com.boussas.tasks.mapper.impl;

import com.boussas.tasks.mapper.TaskListMapper;
import com.boussas.tasks.model.TaskList;
import com.boussas.tasks.model.User;
import com.boussas.tasks.model.dto.TaskListDTO;
import org.springframework.stereotype.Service;

@Service
public class TaskListMapperImpl implements TaskListMapper {

    @Override
    public TaskListDTO toDto(TaskList taskList) {
        if (taskList == null) return null;
        return TaskListDTO.builder()
                .id(taskList.getId())
                .title(taskList.getTitle())
                .description(taskList.getDescription())
                .createdAt(taskList.getCreatedAt())
                .updatedAt(taskList.getUpdatedAt())
                .userId(taskList.getOwner() != null ? taskList.getOwner().getId() : null)

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
                .owner(dto.getUserId() != null ? User.builder().id(dto.getUserId()).build() : null)
                .build();
    }

}
