package com.missedfaces.server.integration.transformers;

import com.google.common.collect.Maps;
import com.missedfaces.server.domain.dto.Notification;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UrlPostNotificationTransformer {

  public Message<?> transform(Message<?> message) {
    Notification notification = (Notification) message.getPayload();
    Map<String, Object> headers = Maps.newHashMap();
    headers.put("URL", notification.getDestination());
    headers.put("Content-Type", MediaType.APPLICATION_JSON);
    return new GenericMessage<>(notification.getDetection(), headers);
  }
}
