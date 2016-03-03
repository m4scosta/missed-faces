package com.missedfaces.server.domain.beans;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public @Data class Detection {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  private Long id;

  @Getter
  private Date receiveTime = new Date();

  @NotNull
  private Date time;

  @NotNull
  private Byte[] image;

  @NotNull
  @ManyToOne(targetEntity = MissedPerson.class, optional = false, fetch = FetchType.EAGER)
  private MissedPerson person;

  @ManyToOne(targetEntity = Detector.class, optional = false, fetch = FetchType.EAGER)
  private Detector detector;

  // TODO: fazer ligacao com o usuario
}
