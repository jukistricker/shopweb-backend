package com.project.shopapp.aspect;

import com.project.shopapp.service.AppLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private AppLogService logService;

    @Pointcut("@annotation(loggable)")
    public void loggableMethods(Loggable loggable) {}

    @Before("loggableMethods(loggable)")
    public void logBefore(JoinPoint joinPoint, Loggable loggable) {
        String methodName = joinPoint.getSignature().getName();
        String actionDesc = loggable.value();
        logService.logInfo("Thực hiện: " + (actionDesc.isEmpty() ? methodName : actionDesc));
    }

    @AfterThrowing(pointcut = "loggableMethods(loggable)", throwing = "e")
    public void logError(JoinPoint joinPoint, Loggable loggable, Throwable e) {
        String methodName = joinPoint.getSignature().getName();
        logService.logError("Lỗi ở " + methodName + ": " + e.getMessage());
    }
}
