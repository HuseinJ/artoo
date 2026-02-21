package com.hjusic.artoo.consumer;

import com.hjusic.artoo.email.EmailService;
import com.hjusic.artoo.model.EmailNotificationMessage;
import com.hjusic.artoo.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationConsumer {

  private static final Logger log = LoggerFactory.getLogger(EmailNotificationConsumer.class);

  private final EmailService emailService;

  public EmailNotificationConsumer(EmailService emailService) {
    this.emailService = emailService;
  }

  @KafkaListener(
      topics = "${artoo.kafka.topic}",
      groupId = "${spring.kafka.consumer.group-id}"
  )
  public void consume(@Payload Notification message) {
    emailService.send(message);
  }

}
