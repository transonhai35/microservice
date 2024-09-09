package com.cloud.api_gateway.repository;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import com.cloud.api_gateway.DTO.IntrospectDTO;
import com.cloud.api_gateway.DTO.response.ApiResponse;
import com.cloud.api_gateway.DTO.response.IntrospectResponse;

import reactor.core.publisher.Mono;

public interface MainClient {

    @PostExchange(url = "/auth/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectDTO dto);    
}
