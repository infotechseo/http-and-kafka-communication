package com.example.user_service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final RestTemplate restTemplate;

    public UserController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @PostMapping("/{userId}/orders")
    public String placeOrder(@PathVariable String userId) {
        String orderServiceUrl = "http://localhost:8082/api/orders/create";
        Map<String, String> request = Map.of("userId", userId);
        return restTemplate.postForObject(orderServiceUrl, request, String.class);
    }
}
