package com.missedfaces.server.domain.beans;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class MissedPersonImage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private byte[] data;
}
