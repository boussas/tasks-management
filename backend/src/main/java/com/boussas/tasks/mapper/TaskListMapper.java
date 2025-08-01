package com.boussas.tasks.mapper;

import com.boussas.tasks.model.TaskList;
import com.boussas.tasks.model.dto.TaskListDTO;

public interface TaskListMapper {
    TaskListDTO toDto(TaskList taskList);
    TaskList fromDto(TaskListDTO taskListDTO);
}
