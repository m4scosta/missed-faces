package com.missedfaces.server.integration.gateways;

import com.missedfaces.server.domain.dto.Notification;
import org.springframework.integration.annotation.Gateway;

public interface NotificationGateway {

  @Gateway(requestChannel = "notificationChannel")
  void sendNotification(Notification notification);
}
