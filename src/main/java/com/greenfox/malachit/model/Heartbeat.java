package com.greenfox.malachit.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Heartbeat {
  private String status;
  private String database;

  public Heartbeat() {}

  public Heartbeat(String status, String database) {
    this.status = status;
    this.database = database;
  }
}
