package com.stowa.FlareAPI.exception;

import com.stowa.FlareAPI.model.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

  @ExceptionHandler(EmployeeNotFoundException.class)
  public ResponseEntity<ApiResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
    ApiResponse apiResponse = new ApiResponse(false, ex.getMessage());
    return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DepartmentNotFoundException.class)
  public ResponseEntity<ApiResponse> handleDepartmentNotFoundException(DepartmentNotFoundException dx){
    ApiResponse apiResponse = new ApiResponse(false, dx.getMessage());
    return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException rx){
    ApiResponse apiResponse = new ApiResponse(false, rx.getMessage());
    return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException mx){
    Map<String, String> errors = new HashMap<>();
    mx.getBindingResult().getAllErrors().forEach((error) ->{
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName,errorMessage);
    });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}
