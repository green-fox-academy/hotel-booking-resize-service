package com.greenfox.malachit.service;

public class LogCreator {

  private static final String APPNAME = "hotel-booking-resize-service.herokuapp.com";
  private String logLevel;
  private String date;
  private String message;

  public LogCreator(String logLevel, String date, String message) {
    this.date = date;
    this.message = message;
    this.logLevel = logLevel;
  }

  public String getLog() {
    return logLevel + " " + date + " " + APPNAME + " " + message;
  }
}
