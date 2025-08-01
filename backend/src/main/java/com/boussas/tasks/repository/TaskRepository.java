package com.boussas.tasks.repository;

import com.boussas.tasks.model.Task;
import com.boussas.tasks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    Optional<Task> findById(UUID id);

    void deleteById(UUID id);

    Task  save(Task task);

    List<Task> findByCreatedBy(User user);
}
