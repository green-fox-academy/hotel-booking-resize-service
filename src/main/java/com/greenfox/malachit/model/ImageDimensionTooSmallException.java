package com.greenfox.malachit.model;

public class ImageDimensionTooSmallException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public ImageDimensionTooSmallException() {
    super("The image cannot be smaller than 200x150");
  }
}
