package com.greenfox.malachit.model;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class Hearthbeat{
  private String status;
  private String database;
  private String queue;

  public Hearthbeat() {
    this.status = "ok";
    this.database = "ok";
    this.queue = "ok";
  }

  public Hearthbeat(String status, String database, String queue) {
    this.status = status;
    this.database = database;
    this.queue = queue;
  }


}
