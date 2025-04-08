package com.project.shopapp.exception;

import com.project.shopapp.constants.ApiConstants;
import com.project.shopapp.dto.Meta;
import com.project.shopapp.dto.ResponseMessageDto;
import jakarta.persistence.EntityNotFoundException;
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
    @ExceptionHandler({EntityNotFoundException.class, ChangeSetPersister.NotFoundException.class})
    public ResponseEntity<ResponseMessageDto> handleNotFoundExceptions(Exception ex) {
        ResponseMessageDto res = new ResponseMessageDto(
                new Meta(StatusCode.Error404, MessageResource.NOT_FOUND),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseMessageDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessageDto> handleValidationException(MethodArgumentNotValidException ex) {
        ResponseMessageDto res= new ResponseMessageDto(
                new Meta(StatusCode.Error400, MessageResource.ERROR_400_MESSAGE),
                ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    // Xử lý lỗi chung cho toàn bộ hệ thống
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessageDto> handleGlobalException(Exception ex) {
    ResponseMessageDto res = new ResponseMessageDto(
            new Meta(StatusCode.Error500,  ex.getMessage()),
            ex.getMessage()
    );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }

}
