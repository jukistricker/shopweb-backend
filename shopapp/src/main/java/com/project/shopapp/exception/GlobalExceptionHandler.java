package com.project.shopapp.exception;

import com.project.shopapp.dto.ResponseMessageDto;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.project.shopapp.constants.ApiConstants.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ResponseMessageDto> buildErrorResponse(HttpStatus status, String message) {
        ResponseMessageDto response = new ResponseMessageDto().Error(message, status.value());
        return ResponseEntity.status(status).body(response);
    }

    // Xử lý lỗi khi không tìm thấy dữ liệu
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ResponseMessageDto> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseMessageDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessageDto> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
    }

    // Xử lý lỗi chung cho toàn bộ hệ thống
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessageDto> handleGlobalException(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
