package com.cloud.api_gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;

import com.cloud.api_gateway.service.MainService;

import reactor.core.publisher.Mono;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Component
@Slf4j
@AllArgsConstructor
public class AuthenticationFilter implements GlobalFilter, Ordered {
    
    private final MainService mainSvc;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("Enter authentiaction filter");

        // Get token from authentication header
        List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        if(CollectionUtils.isEmpty(authHeader))
            return unauthenticated(exchange.getResponse());
        
            String token = authHeader.getFirst().replace("Bearer", "");
            log.info("Token: {}", token);
            
        // Verify token
        // Delegate main service

        //

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    Mono<Void> unauthenticated(ServerHttpResponse response){
        
        String body = "Unauthenticated";
        response.setStatusCode(HttpStatus.UNAUTHORIZED);

        return response.writeWith(Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }

}
