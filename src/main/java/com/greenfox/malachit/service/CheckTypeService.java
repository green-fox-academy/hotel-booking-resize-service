package com.greenfox.malachit.service;

import org.springframework.stereotype.Service;

@Service
public class CheckTypeService {

  public String checkIfImage(String toCheck) {
    return toCheck.contains("image") ? "image" : toCheck;
  }
}
