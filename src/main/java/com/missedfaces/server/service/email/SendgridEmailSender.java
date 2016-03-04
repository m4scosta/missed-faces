package com.missedfaces.server.service.email;

import com.missedfaces.server.domain.dto.Notification;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class SendgridEmailSender implements EmailSender {

  @Value("${sendgrid.api_key}")
  private String API_KEY;

  @Override
  public void sendEmail(Message<Notification> message) {
    SendGrid sendGrid = new SendGrid(API_KEY);
    SendGrid.Email email = new SendGrid.Email();
    email.setFrom("notification@missedfaces.com");
    email.setSubject("Missed person detected");
    email.addTo(message.getPayload().getDestination());
    // TODO: improve email text
    email.setText(message.getPayload().toString());

    try {
      SendGrid.Response response = sendGrid.send(email);
      System.out.println(response.getMessage());
      System.out.println(response.getCode());
      System.out.println(response.getStatus());
    } catch (SendGridException e) {
      e.printStackTrace();
    }
  }
}
