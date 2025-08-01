package com.boussas.tasks.repository;

import com.boussas.tasks.model.TaskList;
import com.boussas.tasks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListRepository extends JpaRepository<TaskList, UUID> {
    List<TaskList> findByOwner(User user);
    Optional<TaskList> findById(UUID id);
}
