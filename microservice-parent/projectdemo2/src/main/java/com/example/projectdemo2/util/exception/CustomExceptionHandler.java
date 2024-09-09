package com.example.projectdemo2.util.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.projectdemo2.dto.ResponseValidDTO;
import com.example.projectdemo2.dto.response.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.*;

import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class CustomExceptionHandler {
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseValidDTO handleValidationException(MethodArgumentNotValidException ex){
        
        List<ErrorResponse> errors = new ArrayList<>();
        // Iterate through all validation errors
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            // Extract field name and error message
            String fieldName = ((FieldError) error).getField();
            // Resolve the error message from the messageSource using the provided locale
            String defaultMessage = error.getDefaultMessage();
            // Add error details to the list of ErrorResponse
            errors.add(new ErrorResponse(fieldName, defaultMessage));
        });
        // Return a ResponseValidDTO containing the list of errors
        return new ResponseValidDTO(errors);
    }


}
