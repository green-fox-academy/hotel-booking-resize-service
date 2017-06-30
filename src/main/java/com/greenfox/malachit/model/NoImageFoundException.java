package com.greenfox.malachit.model;

public class NoImageFoundException extends RuntimeException{
  private static final long serialVersionUID = 1L;
  private String errorMessage;

  public NoImageFoundException(String id) {
    this.errorMessage = "No thumbnails found by id: " + id;
  }

  public String getMessage() {
    return errorMessage;
  }
}
