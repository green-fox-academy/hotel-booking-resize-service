package com.greenfox.malachit.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ThumbnailResponse<T> extends LinkResponse {
  T data;

  public ThumbnailResponse(SelfUrl links, T data) {
    super(links);
    this.data = data;
  }

  public ThumbnailResponse(T data) {
    this.data = data;
  }
}
