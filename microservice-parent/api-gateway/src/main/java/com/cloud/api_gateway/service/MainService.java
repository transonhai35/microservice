package com.cloud.api_gateway.service;

import com.cloud.api_gateway.DTO.response.ApiResponse;
import com.cloud.api_gateway.DTO.response.IntrospectResponse;

import reactor.core.publisher.Mono;

public interface MainService {
    
    Mono<ApiResponse<IntrospectResponse>> introspect(String token);
}
