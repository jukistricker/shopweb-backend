package com.project.shopapp.repository;

import com.project.shopapp.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntry, Long> {
}

