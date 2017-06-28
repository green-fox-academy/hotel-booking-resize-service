package com.greenfox.malachit.model;

public class ThumbnailAttributesDTO {
  private boolean is_main;
  private boolean uploaded;
  private String created_at;
  private String content_url;

  public ThumbnailAttributesDTO() {}

  public ThumbnailAttributesDTO(boolean is_main, boolean uploaded, String created_at, String content_url) {
    this.is_main = is_main;
    this.uploaded = uploaded;
    this.created_at = created_at;
    this.content_url = content_url;
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
}
