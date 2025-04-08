package com.project.shopapp.service.impl;


import com.project.shopapp.entity.LogEntry;
import com.project.shopapp.repository.LogRepository;
import com.project.shopapp.service.AppLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppLogServiceImpl implements AppLogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public void logInfo(String message) {
        saveLog("INFO", message);
    }

    @Override
    public void logError(String message) {
        saveLog("ERROR", message);
    }

    @Override
    public void logDebug(String message) {
        saveLog("DEBUG", message);
    }

    @Override
    public void logWarn(String message) {
        saveLog("WARN", message);
    }

    private void saveLog(String level, String message) {
        LogEntry log = new LogEntry();
        log.setLevel(level);
        log.setMessage(message);
        log.setTimestamp(LocalDateTime.now());
        logRepository.save(log);
    }
}