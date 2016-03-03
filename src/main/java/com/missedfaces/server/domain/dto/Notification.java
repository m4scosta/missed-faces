package com.missedfaces.server.domain.dto;

import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.domain.enums.NotificationType;
import lombok.Data;

@Data
public class Notification {

  private NotificationType type;
  private String destination;
  private Detection detection;
}
