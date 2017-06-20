package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.Hearthbeat;
import com.greenfox.malachit.service.HearthBeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HeartbeatRestController {

  private Logger logger = LoggerFactory.getLogger(HeartbeatRestController.class);
  private HearthBeatService hearthBeatService;

  @Autowired
  public HeartbeatRestController(HearthBeatService hearthBeatService) {
    this.hearthBeatService = hearthBeatService;
  }

  @GetMapping("/")
  public Hearthbeat indexHello() {
    logger.info("HTTP-REQUEST / works as intended");
    return new Hearthbeat("Hello", "Hello");
  }

  @GetMapping("/heartbeat")
  public Hearthbeat healthCheck() {
    logger.info("HTTP-REQUEST /heartbeat works as intended");
    return hearthBeatService.healthStatus();
  }
}
