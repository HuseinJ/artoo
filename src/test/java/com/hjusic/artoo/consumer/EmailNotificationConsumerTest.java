package com.hjusic.artoo.consumer;

import com.hjusic.artoo.email.EmailService;
import com.hjusic.artoo.model.Notification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailNotificationConsumerTest {

  @Mock
  private EmailService emailService;

  @InjectMocks
  private EmailNotificationConsumer emailNotificationConsumer;

  @Test
  void consume_shouldCallEmailService() {
    // given
    Notification notification = new Notification(
        "123",
        "email",
        "recipient@example.com",
        "sender@example.com",
        "Test Subject",
        "Test Content"
    );

    // when
    emailNotificationConsumer.consume(notification);

    // then
    verify(emailService).send(notification);
  }
}
