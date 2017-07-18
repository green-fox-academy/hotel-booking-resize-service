package com.greenfox.malachit.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorReturnObject {
  private String status;
  private String title;
  private String detail;

  public ErrorReturnObject() {}

  public ErrorReturnObject(String status, String title, String detail) {
    this.status = status;
    this.title = title;
    this.detail = detail;
  }
}
