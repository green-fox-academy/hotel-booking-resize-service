package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.Hearthbeat;
import com.greenfox.malachit.service.LogLine;
import com.greenfox.malachit.service.HearthBeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartbeatRestController {

  private HearthBeatService hearthBeatService;

  @Autowired LogLine logLine;

  @Autowired
  public HeartbeatRestController(HearthBeatService hearthBeatService) {
    this.hearthBeatService = hearthBeatService;
  }

  @GetMapping("/")
  public Hearthbeat indexHello() {
    logLine.printInfoLog("/ endpoint works well");
    return new Hearthbeat("Hello", "Hello");
  }

  @GetMapping("/heartbeat")
  public Hearthbeat healthCheck() {
    logLine.printInfoLog("/heartbeat endpoint works well");
    return hearthBeatService.healthStatus();
  }
}
