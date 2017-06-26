package com.greenfox.malachit.service;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EventCreator {
  private static final String HOST = "resize-host-name-here";

  private String time;
  private String hostname;
  private String message;

  public EventCreator(String message) {
    this.message = message;
    this.time = new Date().toString();
    this.hostname = HOST;
  }

  public EventCreator() {}

  public static String getHOST() {
    return HOST;
  }

  public String getTime() {
    return time;
  }

  public String getHostname() {
    return hostname;
  }

  public String getMessage() {
    return message;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String createEventString() {
    JSONObject json = new JSONObject();
    json.put("time", this.getTime());
    json.put("hostname", this.getHostname());
    json.put("message", this.getMessage());
    return json.toJSONString();
  }
}
