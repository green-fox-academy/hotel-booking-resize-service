package com.greenfox.malachit.model;

public class FileTooLargeException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public FileTooLargeException() {
    super("The image cannot be bigger than 1MB");
  }
}
