package com.boussas.tasks.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color;

    @ManyToMany(mappedBy = "tags")
    private Set<Task> tasks = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
