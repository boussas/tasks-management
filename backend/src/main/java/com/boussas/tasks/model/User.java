package com.boussas.tasks.model;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Tag> tags;

    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY)
    private List<TaskList> taskLists;

    @OneToMany(mappedBy = "createdBy",fetch = FetchType.LAZY)
    private List<Task> tasks;
}
