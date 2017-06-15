package com.greenfox.malachit.service;

import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateCreator {

  public DateCreator() {
  }

  public String createDate(){
    ZonedDateTime dateTime = ZonedDateTime.now();
    return dateTime.format(DateTimeFormatter.ISO_INSTANT);
  }
}
