package com.boussas.tasks.model;

import com.boussas.tasks.model.Tag;
import com.boussas.tasks.model.Task;
import com.boussas.tasks.model.TaskList;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Slf4j
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Tag> tags;

    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY)
    private List<TaskList> taskLists;

    @OneToMany(mappedBy = "createdBy",fetch = FetchType.LAZY)
    private List<Task> tasks;
}
