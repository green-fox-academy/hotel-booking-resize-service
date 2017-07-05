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
  private long id;
  private String uniqueName;

  public ImageData(long id, String uniqueName) {
    this.id = id;
    this.uniqueName = uniqueName;
  }
}
