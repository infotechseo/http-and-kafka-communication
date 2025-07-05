package com.example.order_service;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Map<String, String> payload) {
        String userId = payload.get("userId");
        String message = "Order placed by user: " + userId;

        kafkaTemplate.send("order-topic", message);

        return ResponseEntity.ok("Order created & event sent to Kafka");
    }
}

