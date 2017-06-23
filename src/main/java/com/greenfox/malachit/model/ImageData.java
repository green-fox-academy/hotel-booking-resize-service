package com.greenfox.malachit.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ImageData {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String url;

  public ImageData(String url) {
    this.url = url;
  }
}
