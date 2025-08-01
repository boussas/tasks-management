package com.boussas.tasks.mapper;

import com.boussas.tasks.model.User;
import com.boussas.tasks.model.dto.UserDTO;

public interface UserMapper {
    UserDTO toDto(User user);
    User fromDto(UserDTO userDTO);
}
