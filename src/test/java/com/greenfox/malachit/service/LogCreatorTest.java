package com.greenfox.malachit.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class LogCreatorTest {
  private LogCreator logCreator = new LogCreator("INFO", "2017-06-15T13:50:29.072Z", "hello");

  @Test
  public void getLogOkLog() throws Exception {
    assertEquals("INFO 2017-06-15T13:50:29.072Z hotel-booking-resize-service.herokuapp.com hello",
            logCreator.getLog());
  }
}