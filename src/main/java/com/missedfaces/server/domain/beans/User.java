package com.missedfaces.server.domain.beans;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "users")
public class User {

  @Id
  @NotNull
  private String username;

  @NotNull
  @NotEmpty
  private String password;

  @NotNull
  private boolean enabled;
}
