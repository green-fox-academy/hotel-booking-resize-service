package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.Hearthbeat;
import com.greenfox.malachit.service.HearthBeatService;
import com.greenfox.malachit.service.MessageQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HeartbeatRestController {

  private Logger logger = LoggerFactory.getLogger(HeartbeatRestController.class);
  private HearthBeatService hearthBeatService;
  private MessageQueueService messageQueueService = new MessageQueueService();

  @Autowired
  public HeartbeatRestController(HearthBeatService hearthBeatService) {
    this.hearthBeatService = hearthBeatService;
  }

  @GetMapping("/")
  public Hearthbeat indexHello() {
    logger.info("/ works as intended");
    try {messageQueueService.sendMessage();
    } catch (Exception e){
      System.out.println(e.getClass());
    }
    return new Hearthbeat("Hello", "Hello");
  }

  @GetMapping("/heartbeat")
  public Hearthbeat healthCheck() {
    logger.error("asdasdasd");
    return hearthBeatService.healthStatus();
  }
}
