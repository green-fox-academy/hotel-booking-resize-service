package com.greenfox.malachit.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ImageExtensionNotValidException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public ImageExtensionNotValidException() {
    super("The image type should be one of the following: jpeg, gif, png");
  }
}
