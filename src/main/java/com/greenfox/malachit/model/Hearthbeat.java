package com.greenfox.malachit.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
public class Hearthbeat {
  private String status;
  private String database;

  public Hearthbeat() {}

  public Hearthbeat(String status, String database) {
    this.status = status;
    this.database = database;
  }
}
