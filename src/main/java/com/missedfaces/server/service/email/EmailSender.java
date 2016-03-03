package com.missedfaces.server.service.email;

import com.missedfaces.server.domain.dto.Notification;
import org.springframework.messaging.Message;

public interface EmailSender {

  void sendEmail(Message<Notification> message);
}
