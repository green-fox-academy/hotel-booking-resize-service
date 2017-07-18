package com.greenfox.malachit.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
public class ImageDimensionTooSmallException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public ImageDimensionTooSmallException() {
    super("The image cannot be smaller than 200x150");
  }
}
