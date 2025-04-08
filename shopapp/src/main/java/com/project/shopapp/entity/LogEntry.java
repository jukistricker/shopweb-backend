package com.project.shopapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
@Data
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level;

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime timestamp;

    // constructors, getters, setters
}

