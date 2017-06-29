package com.greenfox.malachit.model;

public class NoImageFoundException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public NoImageFoundException(String id) {
    super("Could not find image " + id);
  }
}
