package com.greenfox.malachit.service;

import com.greenfox.malachit.model.ImageData;
import com.greenfox.malachit.repository.ImageDataRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;

@Service
@NoArgsConstructor
public class UniqueName {

  private static final String NAMECHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private static int NAMELENGTH = 12;
  private static String SEPARATOR = ".jp";
  private static SecureRandom srnd = new SecureRandom();


  private ImageDataRepository imageDataRepository;

  public UniqueName(ImageDataRepository imageDataRepository) {
    this.imageDataRepository = imageDataRepository;
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
    if (imageDataRepository.count() > 0) {
      ArrayList<ImageData> imageDataArrayList = imageDataRepository.findAll();
      for (ImageData actual : imageDataArrayList) {
        String actualUniqueName = actual.getUniqueName();
        if (name.equals(actualUniqueName)) {
          random = false;
        }
      }
    }
    return random;
  }
}
