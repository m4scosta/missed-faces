package com.missedfaces.server.domain.beans;

import com.missedfaces.server.domain.enums.NotificationType;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Notification {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private NotificationType type;

  @NotNull
  @NotEmpty
  private String target;

  @ManyToOne(targetEntity = User.class)
  private User user;
}
