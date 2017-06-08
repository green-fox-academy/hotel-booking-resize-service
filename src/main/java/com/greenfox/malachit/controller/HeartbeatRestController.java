package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.Heartbeat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartbeatRestController {

  @GetMapping("/hearthbeat")

  public Heartbeat healthCheck() {
    return new Heartbeat("ok", "ok");
  }
}
