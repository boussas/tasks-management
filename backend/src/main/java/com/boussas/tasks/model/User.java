package com.boussas.tasks.model;

import com.boussas.tasks.model.Tag;
import com.boussas.tasks.model.Task;
import com.boussas.tasks.model.TaskList;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "user")
    private List<Tag> tags;

    @OneToMany(mappedBy = "owner")
    private List<TaskList> taskLists;

    @OneToMany(mappedBy = "createdBy")
    private List<Task> tasks;
}
