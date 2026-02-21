package com.hjusic.artoo.model;

public record Notification(
    String id,
    String type,
    String recipient,
    String sender,
    String subject,
    String content
){}