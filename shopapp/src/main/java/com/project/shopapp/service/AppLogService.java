package com.project.shopapp.service;

public interface AppLogService {
    void logInfo(String message);
    void logError(String message);
    // Thêm nếu cần
    void logDebug(String message);
    void logWarn(String message);
}

