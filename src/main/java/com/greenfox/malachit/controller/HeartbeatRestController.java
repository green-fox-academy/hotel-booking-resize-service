package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.Hearthbeat;
import com.greenfox.malachit.service.HearthBeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartbeatRestController {

  private HearthBeatService hearthBeatService;

  @Autowired
  public HeartbeatRestController(HearthBeatService hearthBeatService) {
    this.hearthBeatService = hearthBeatService;
  }

  @GetMapping("/")
  public Hearthbeat indexHello() {
    return new Hearthbeat("Hello", "Hello");
  }

  @GetMapping("/hearthbeat")
  public Hearthbeat healthCheck() {
    return hearthBeatService.healthStatus();
  }
}
