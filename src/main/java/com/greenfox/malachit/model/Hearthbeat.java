package com.greenfox.malachit.model;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class Hearthbeat{
  private String status;
  private String database;

  public Hearthbeat() {
    this.status = "ok";
    this.database = "ok";
  }

  public Hearthbeat(String status, String database) {
    this.status = status;
    this.database = database;
  }


}
