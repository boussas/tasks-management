package com.boussas.tasks.controller;

import com.boussas.tasks.mapper.TaskListMapper;
import com.boussas.tasks.model.TaskList;
import com.boussas.tasks.model.User;
import com.boussas.tasks.model.dto.TaskListDTO;
import com.boussas.tasks.service.TaskListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task-lists")
public class TaskListController {

    private final TaskListService service;
    private final TaskListMapper mapper;

    public TaskListController(TaskListService service, TaskListMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<TaskListDTO> create(@RequestBody TaskListDTO dto) {
        TaskList created = service.createTaskList(mapper.fromDto(dto));
        return ResponseEntity.ok(mapper.toDto(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskListDTO> getById(@PathVariable UUID id) {
        return service.getTaskListById(id)
                .map(taskList -> ResponseEntity.ok(mapper.toDto(taskList)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskListDTO> update(@PathVariable UUID id, @RequestBody TaskListDTO dto) {
        TaskList taskList = mapper.fromDto(dto);
        taskList.setId(id);
        TaskList updated = service.updateTaskList(taskList);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteTaskList(id);
        return ResponseEntity.noContent().build();
    }
}
