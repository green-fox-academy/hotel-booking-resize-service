package com.greenfox.malachit.service;

import com.greenfox.malachit.model.Hearthbeat;
import com.greenfox.malachit.repository.HealthCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HearthBeatService {

  private HealthCheckRepository healthCheckRepository;
  private MessageQueueService messageQueueService;

  @Autowired
  public HearthBeatService(HealthCheckRepository healthCheckRepository, MessageQueueService messageQueueService){
    this.healthCheckRepository = healthCheckRepository;
    this.messageQueueService = messageQueueService;
  }

  public Hearthbeat healthStatus() throws Exception {
    Hearthbeat hearthbeatResponse = new Hearthbeat();
    if (healthCheckRepository.findAllByOrderById().size() < 1) {
      hearthbeatResponse.setDatabase("error");
    }
    try {
      messageQueueService.sendMessage();
    } catch (Exception e) {
      hearthbeatResponse.setQueue("error");
    }
    try {
      messageQueueService.receiveMessage();
    } catch (Exception e) {
      hearthbeatResponse.setQueue("error");
    }
    if (messageQueueService.messagesInQueue() > 0) {
      hearthbeatResponse.setQueue("error");
    }
      return hearthbeatResponse;
  }
}
