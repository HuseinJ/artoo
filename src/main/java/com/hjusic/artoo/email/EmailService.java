package com.hjusic.artoo.email;

import com.hjusic.artoo.model.Notification;

public interface EmailService {
  void send(Notification message);
}
