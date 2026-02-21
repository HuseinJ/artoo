package com.hjusic.artoo.email;

import com.hjusic.artoo.model.Notification;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

  @Mock
  private JavaMailSender mailSender;

  @Mock
  private TemplateRenderingService templateRenderingService;

  @Mock
  private MimeMessage mimeMessage;

  @InjectMocks
  private EmailServiceImpl emailService;

  @Test
  void send_shouldRenderTemplateAndSendEmail() throws MessagingException {
    // given
    Notification notification = new Notification(
        "123",
        "email",
        "recipient@example.com",
        "sender@example.com",
        "Test Subject",
        "Test Content"
    );

    String renderedHtml = "<html>Test Content</html>";
    when(templateRenderingService.render(eq("emails/notification.jte"), eq(notification)))
        .thenReturn(renderedHtml);
    when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

    // when
    emailService.send(notification);

    // then
    verify(templateRenderingService).render("emails/notification.jte", notification);
    verify(mailSender).createMimeMessage();
    verify(mailSender).send(mimeMessage);
  }

  @Test
  void send_shouldLogErrorWhenMessagingExceptionOccurs() {
    // given
    Notification notification = new Notification(
        "123",
        "email",
        "recipient@example.com",
        "sender@example.com",
        "Test Subject",
        "Test Content"
    );

    String renderedHtml = "<html>Test Content</html>";
    when(templateRenderingService.render(eq("emails/notification.jte"), eq(notification)))
        .thenReturn(renderedHtml);
    when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
    doThrow(new RuntimeException("Mail server error"))
        .when(mailSender).send(any(MimeMessage.class));

    // when
    emailService.send(notification);

    // then
    verify(mailSender).send(mimeMessage);
    // Exception should be caught and logged, not thrown
  }
}
