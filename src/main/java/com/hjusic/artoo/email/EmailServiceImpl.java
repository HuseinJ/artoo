package com.hjusic.artoo.email;

import com.hjusic.artoo.model.Notification;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

  private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
  private final JavaMailSender mailSender;
  private final TemplateRenderingService templateRenderingService;

  public EmailServiceImpl(JavaMailSender mailSender,
      TemplateRenderingService templateRenderingService) {
    this.mailSender = mailSender;
    this.templateRenderingService = templateRenderingService;
  }

  @Override
  public void send(Notification notification) {
    try {
      var html = templateRenderingService.render("emails/notification.jte", notification);

      var mimeMessage = mailSender.createMimeMessage();
      var helper = new MimeMessageHelper(mimeMessage, "UTF-8");
      helper.setFrom(notification.sender());
      helper.setTo(notification.recipient());
      helper.setSubject(notification.subject());
      helper.setText(html, true);

      mailSender.send(mimeMessage);
      log.info(" Sent email to {} with subject: {}", notification.recipient(), notification.subject());
    } catch (MessagingException | RuntimeException e) {
      log.error("Failed to send email to {}. With exception: ", notification.recipient(), e);
    }

  }
}
