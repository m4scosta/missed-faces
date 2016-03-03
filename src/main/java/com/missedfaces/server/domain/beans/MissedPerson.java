package com.missedfaces.server.domain.beans;

import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public @Data class MissedPerson {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  private Long id;

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  private Date bornDate;

  @NotNull
  private Date missedDate;

  @NotNull
  private Byte[] image; // TODO: make it a List

  // TODO: verificar para que foi usado o counter
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Getter
  private Long counter;

  // TODO: fazer ligacao com o usuario
}
