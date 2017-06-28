package com.greenfox.malachit.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ThumbnailAttributes {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String type;
  private boolean is_main;
  private boolean uploaded;
  private String created_at;
  private String content_url;

  public ThumbnailAttributes() {}
}
