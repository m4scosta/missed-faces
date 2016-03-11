package com.missedfaces.server.integration.transformers;

import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.domain.dto.Notification;
import com.missedfaces.server.domain.enums.NotificationType;
import org.springframework.stereotype.Component;

@Component
public class DetectionToNotificationTransformer {

  public Notification toNotification(Detection detection) {
    Notification notification = new Notification();
    notification.setType(NotificationType.EMAIL);
    notification.setDestination("costa.marcos.pro@gmail.com");
    notification.setDetection(detection);
    return notification;
  }
}
