package com.missedfaces.server.domain.beans;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public @Data class MissedPerson {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  private Date bornDate;

  @NotNull
  private Date missedDate;

  @OneToMany(targetEntity = MissedPersonImage.class, fetch = FetchType.EAGER)
  private List<MissedPersonImage> images;

  // TODO: verificar para que foi usado o counter
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long counter;

  @ManyToOne(targetEntity = User.class)
  private User user;

  @Cascade(value = CascadeType.REMOVE)
  @OneToMany(targetEntity = Detection.class, mappedBy = "person", fetch = FetchType.EAGER, orphanRemoval = true)
  private List<Detection> detections;

  @Override
  public String toString() {
    return "MissedPerson{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", bornDate=" + bornDate +
        ", missedDate=" + missedDate +
        ", counter=" + counter +
        ", user=" + user.getId() +
        '}';
  }
}
