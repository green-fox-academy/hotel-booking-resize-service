package com.greenfox.malachit.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ThumbnailAttributes {
  @Id
  private long id;
  private String type;
  private boolean is_main;
  private boolean uploaded;
  private String created_at;
  private String content_url;

  public ThumbnailAttributes() {}
}
