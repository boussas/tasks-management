package com.boussas.tasks.mappers.impl;

import com.boussas.tasks.mappers.TagMapper;
import com.boussas.tasks.mappers.TaskMapper;
import com.boussas.tasks.mappers.UserMapper;
import com.boussas.tasks.model.Tag;
import com.boussas.tasks.model.dto.TagDTO;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

public class TagMapperImpl implements TagMapper {

    private final TaskMapper taskMapper;
    private final UserMapper userMapper;

    public TagMapperImpl(TaskMapper taskMapper, UserMapper userMapper) {
        this.taskMapper = taskMapper;
        this.userMapper = userMapper;
    }

    @Override
    public TagDTO toDto(Tag tag) {
        if (tag == null) return null;

        return TagDTO.builder()
                .id(tag.getId())
                .name(tag.getName())
                .color(tag.getColor())
                .tasks(tag.getTasks() != null
                        ? tag.getTasks().stream().map(taskMapper::toDto).collect(Collectors.toSet())
                        : null)
                .user(userMapper.toDto(tag.getUser()))
                .build();
    }

    @Override
    public Tag fromDto(TagDTO tagDTO) {
        if (tagDTO == null) return null;

        return Tag.builder()
                .id(tagDTO.getId())
                .name(tagDTO.getName())
                .color(tagDTO.getColor())
                .tasks(tagDTO.getTasks() != null
                        ? tagDTO.getTasks().stream().map(taskMapper::fromDto).collect(Collectors.toSet())
                        : null)
                .user(userMapper.fromDto(tagDTO.getUser()))
                .build();
    }
}
