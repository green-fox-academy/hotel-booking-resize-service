package com.greenfox.malachit.service;

import com.greenfox.malachit.repository.ImageDataReposytory;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@NoArgsConstructor
public class UniqueName {

  private static final String NAMECHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private static int NAMELENGTH = 12;
  private static String SEPARATOR = ".jp";
  private static SecureRandom srnd = new SecureRandom();
  private ImageDataReposytory imageDataReposytory;

  @Autowired
  public UniqueName(ImageDataReposytory imageDataReposytory) {
    this.imageDataReposytory = imageDataReposytory;
  }

  public String createUniqueName() {
    String name = randomName();
    while (!isRandom(name)) {
      name = randomName();
    }
    return name;
  }

  private String randomName() {
    StringBuilder name = new StringBuilder(NAMELENGTH);
    for (int i = 0; i < NAMELENGTH; i++) {
      name.append(NAMECHARS.charAt(srnd.nextInt(NAMECHARS.length())));
    }
    return name.toString();
  }

  private boolean isRandom(String name) {
    boolean random = true;
    for (Long i = 0L; i < imageDataReposytory.count(); i++) {
      String url = imageDataReposytory.findOne(i).getUrl();
      String usedName = url.substring(url.indexOf(SEPARATOR) - NAMELENGTH, url.indexOf(SEPARATOR));
      if (name.equals(usedName)) {
        random = false;
      }
    }
    return random;
  }
}
