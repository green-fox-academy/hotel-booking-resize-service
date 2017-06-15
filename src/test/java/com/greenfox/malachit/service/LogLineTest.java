package com.greenfox.malachit.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class LogLineTest {

  @Test
  public void compareLevelsDebug() throws Exception {
    String envVar = "DEBUG";
    LogLine logLine = new LogLine();
    assertFalse(logLine.isLevelValid(envVar));
  }

  @Test
  public void compareLevelsInfo() throws Exception {
    String envVar = "INFO";
    LogLine logLine = new LogLine();
    assertTrue(logLine.isLevelValid(envVar));
  }

  @Test
  public void compareLevelsWarn() throws Exception {
    String envVar = "WARN";
    LogLine logLine = new LogLine();
    assertTrue(logLine.isLevelValid(envVar));
  }

  @Test
  public void compareLevelsError() throws Exception {
    String envVar = "ERROR";
    LogLine logLine = new LogLine();
    assertTrue(logLine.isLevelValid(envVar));
  }
}