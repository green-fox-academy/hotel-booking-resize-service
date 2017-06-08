package com.greenfox.malachit.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HearthBeatNoDatabase {

  private String status;

  public HearthBeatNoDatabase() {
  }

  public HearthBeatNoDatabase(String status) {
    this.status = status;
  }
}
