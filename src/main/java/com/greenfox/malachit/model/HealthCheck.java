package com.greenfox.malachit.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class HealthCheck {

  //@Id
  //@GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  @Column(name="ok")
  private String id;
  //private long id;
  //private boolean ok;

  public HealthCheck() {}

  public HealthCheck(String ok) {
    this.id = ok;
  }
}
