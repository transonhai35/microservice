package com.example.projectdemo2.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseWrapper<T> {

    private int status;
    private String message;
    private T data;
    
}
