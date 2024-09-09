package com.cloud.api_gateway.service.implement;

import org.springframework.stereotype.Service;

import com.cloud.api_gateway.DTO.IntrospectDTO;
import com.cloud.api_gateway.DTO.response.ApiResponse;
import com.cloud.api_gateway.DTO.response.IntrospectResponse;
import com.cloud.api_gateway.repository.MainClient;
import com.cloud.api_gateway.service.MainService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MainServiceImpl implements MainService {


    private final MainClient mainClient;
    
    @Override
    public Mono<ApiResponse<IntrospectResponse>> introspect(String token){

        return mainClient.introspect(new IntrospectDTO(token));
    }
}
