package com.missedfaces.server;

import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.domain.dto.Notification;
import com.missedfaces.server.domain.enums.NotificationType;
import com.missedfaces.server.integration.gateways.NotificationGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = "classpath*:spring/integration.xml")
public class MissedFacesServerApplication {

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(MissedFacesServerApplication.class, args);

    Notification notification = new Notification();
    notification.setDetection(new Detection());
    notification.setDestination("marcos_costa.sjc@hotmail.com");
    notification.setType(NotificationType.EMAIL);

    ctx.getBean(NotificationGateway.class).sendNotification(notification);
  }
}
