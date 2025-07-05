package com.example.notification_service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void consume(String message) {
        System.out.println("📨 Notification Received: " + message);
    }
}