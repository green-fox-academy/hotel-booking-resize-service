package com.greenfox.malachit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ThumbnailAttributes {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private long hotel;
  private String type;
  private boolean is_main;
  private boolean uploaded;
  private String created_at;
  private String content_url;

  public ThumbnailAttributes() {}

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean isIs_main() {
    return is_main;
  }

  public void setIs_main(boolean is_main) {
    this.is_main = is_main;
  }

  public boolean isUploaded() {
    return uploaded;
  }

  public void setUploaded(boolean uploaded) {
    this.uploaded = uploaded;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getContent_url() {
    return content_url;
  }

  public void setContent_url(String content_url) {
    this.content_url = content_url;
  }

  public long getHotel() {
    return hotel;
  }

  public void setHotel(long hotel) {
    this.hotel = hotel;
  }
}
