package com.missedfaces.server.domain.beans;

import lombok.Data;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "users", indexes = @Index(columnList = "username"))
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @NotEmpty
  private String username;

  @NotNull
  @NotEmpty
  private String password;

  @NotNull
  private boolean enabled;

  @Cascade(value = CascadeType.ALL)
  @OneToMany(targetEntity = MissedPerson.class, mappedBy = "user", fetch = FetchType.EAGER)
  private List<MissedPerson> missedPersons;
}
