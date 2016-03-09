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
  private byte[] image;

  @NotNull
  @ManyToOne(targetEntity = MissedPerson.class, optional = false)
  private MissedPerson person;

  @ManyToOne(targetEntity = Detector.class, optional = false)
  private Detector detector;
}
