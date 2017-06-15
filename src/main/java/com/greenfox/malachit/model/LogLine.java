package com.greenfox.malachit.model;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class LogLine {

  private static final String APPNAME = "hotel-booking-resize-service.herokuapp.com";
  private static final String WARN = "WARN";
  private static final String INFO = "INFO";
  private static final String DEBUG = "DEBUG";
  private static final String ERROR = "ERROR";

  public LogLine() {
  }

  public void printInfoLog(String message) {
    if (logLevelNum() < 2) {
      System.out.println(INFO + " " + createDate() + " " + APPNAME + " " + message);
    }
  }

  public void printWarnLog(String message) {
    if (logLevelNum() < 3) {
      System.out.println(WARN + " " + createDate() + " " + APPNAME + " " + message);
    }
  }

  public void printErrorLog(String message) {
    System.out.println(ERROR + " " + createDate() + " " + APPNAME + " " + message);
  }

  public void printDebugLog(String message) {
    if (logLevelNum() < 1) {
      System.out.println(DEBUG + " " + createDate() + " " + APPNAME + " " + message);
    }
  }

  private int logLevelNum() {
    String logLevel = System.getenv("HOTEL_APP_LOG_LEVEL");
    if (logLevel.equals(ERROR)) {
      return 3;
    } else if (logLevel.equals(WARN)) {
      return 2;
    } else if (logLevel.equals(INFO)) {
      return 1;
    } else {
      return 0;
    }
  }

  private String createDate(){
    DateTime dateTime = new DateTime();
    DateTimeFormatter fmt = ISODateTimeFormat.dateTimeNoMillis();
    return fmt.print(dateTime);
  }
}
