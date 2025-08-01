package com.boussas.tasks.mapper.impl;

import com.boussas.tasks.mapper.UserMapper;
import com.boussas.tasks.model.User;
import com.boussas.tasks.model.dto.UserDTO;
import org.springframework.stereotype.Service;
@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(User user) {
        if (user == null) return null;
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

    @Override
    public User fromDto(UserDTO userDTO) {
        if (userDTO == null) return null;
        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                // Don't set password here (never expose in DTO)
                .createdAt(userDTO.getCreatedAt())
                .build();
    }
}
