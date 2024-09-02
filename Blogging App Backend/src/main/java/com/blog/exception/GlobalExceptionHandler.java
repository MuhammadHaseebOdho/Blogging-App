package com.blog.exception;

import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
        ApiResponse apiResponse=new ApiResponse(e.getMessage(),false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        Map<String,String> response=new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(
                (error)->{
                    String fieldName=((FieldError) error).getField();
                    String message= error.getDefaultMessage();
                    response.put(fieldName,message);
                }
        );

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e){
        String errorMessage;

        if (e.getMessage().contains("email")) {
            errorMessage = "This Email Already Exists";
        } else if (e.getMessage().contains("title")) {
            errorMessage = "This Category Title Already Exists";
        } else {
            errorMessage = "Data integrity violation occurred";
        }

        ApiResponse apiResponse = new ApiResponse(errorMessage, false);
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
}
