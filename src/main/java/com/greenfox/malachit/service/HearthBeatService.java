package com.greenfox.malachit.service;

import com.greenfox.malachit.model.Hearthbeat;
import com.greenfox.malachit.repository.HealthCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HearthBeatService {

  private HealthCheckRepository healthCheckRepository;

  @Autowired
  public HearthBeatService(HealthCheckRepository healthCheckRepository){
    this.healthCheckRepository = healthCheckRepository;
  }

  public Hearthbeat healthStatus() {
    if (healthCheckRepository.findAllByOrderById().size() >= 1) {
      return new Hearthbeat("ok", "ok", "ok");
    }
      return new Hearthbeat("ok", "error", "ok");
  }
}
