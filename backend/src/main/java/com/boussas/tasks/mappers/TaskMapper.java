package com.boussas.tasks.mappers;

import com.boussas.tasks.model.Task;
import com.boussas.tasks.model.dto.TaskDTO;

public interface TaskMapper {
    TaskDTO toDto(Task task);
    Task fromDto(TaskDTO taskDTO);
}
