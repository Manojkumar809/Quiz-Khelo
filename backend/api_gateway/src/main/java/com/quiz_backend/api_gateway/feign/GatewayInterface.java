package com.quiz_backend.api_gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVICE", url = "http://localhost:8082")
public interface GatewayInterface {

    @GetMapping("user/validate")
    public ResponseEntity<Boolean> authorizeToken(@RequestHeader("Authorization") String authHeader);
}
