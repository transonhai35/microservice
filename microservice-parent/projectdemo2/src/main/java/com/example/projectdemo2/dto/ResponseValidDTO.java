package com.example.projectdemo2.dto;
import java.util.List;

import com.example.projectdemo2.dto.response.ErrorResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseValidDTO {
    
    private List<ErrorResponse> erros;

}
