package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.HealthCheck;
import com.greenfox.malachit.repository.HealthCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealethRestConroller {

  @Autowired
  HealthCheckRepository healthCheckRepository;

  @GetMapping("/additem")
  public HealthCheck addItem() {
    HealthCheck healthCheckNow = new HealthCheck(1);
    healthCheckRepository.save(healthCheckNow);
    return healthCheckNow;
  }


}
