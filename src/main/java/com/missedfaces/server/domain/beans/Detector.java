package com.missedfaces.server.domain.beans;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public @Data class Detector {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  private Long id;

  @NotNull
  private String description;

  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

  @OneToMany(mappedBy = "detector", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Detection> detections;
}
