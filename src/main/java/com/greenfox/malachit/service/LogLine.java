package com.greenfox.malachit.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class LogLine {

  private static final String APPNAME = "hotel-booking-resize-service.herokuapp.com";
  private static final String WARN = "WARN";
  private static final String INFO = "INFO";
  private static final String DEBUG = "DEBUG";
  private static final String ERROR = "ERROR";
  private static final List<String> levels = Arrays.asList(DEBUG, INFO, WARN, ERROR);

  public LogLine() {
  }

  public void printInfoLog(String message) {
    if (compareLevels(INFO)) {
      printLog(INFO, message);
    }
  }

  public void printWarnLog(String message) {
    if (compareLevels(WARN)) {
      printLog(WARN, message);
    }
  }

  public void printErrorLog(String message) {
    printLog(ERROR, message);
  }

  public void printDebugLog(String message) {
    if (compareLevels(DEBUG)) {
      printLog(DEBUG, message);
    }
  }

  public void printLog(String level, String message) {
    LogCreator logCreator = new LogCreator(level, new DateCreator().createDate(), message);
    if (levels.indexOf(level) > 1) {
      System.err.println(logCreator.getLog());
    } else {
      System.out.println(logCreator.getLog());
    }
  }

  private boolean compareLevels(String envVar) {
    if (levels.indexOf(System.getenv("HOTEL_APP_LOG_LEVEL")) >= levels.indexOf(envVar)) {
      return true;
    } else {
      return false;
    }
  }
}
