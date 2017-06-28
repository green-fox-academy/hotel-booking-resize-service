package com.greenfox.malachit.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ThumbnailAttributesDTO {
  private boolean is_main;
  private boolean uploaded;
  private String created_at;
  private String content_url;

  public ThumbnailAttributesDTO(boolean is_main, boolean uploaded, String created_at, String content_url) {
    this.is_main = is_main;
    this.uploaded = uploaded;
    this.created_at = created_at;
    this.content_url = content_url;
  }
}
