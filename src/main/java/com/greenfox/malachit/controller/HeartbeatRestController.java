package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.Hearthbeat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartbeatRestController {

  @GetMapping("/hearthbeat")

  public Hearthbeat healthCheck() {
    return new Hearthbeat("ok", "ok");
  }
}
