package com.greenfox.malachit.model;

public class ImageExtensionNotValidException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public ImageExtensionNotValidException() {
    super("The image type should be one of the following: jpeg, gif, png");
  }
}
