package com.greenfox.malachit.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class HealthCheck {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;
  private int ok;

  public HealthCheck() {}

  public HealthCheck(int ok) {
    this.ok = ok;
  }
}