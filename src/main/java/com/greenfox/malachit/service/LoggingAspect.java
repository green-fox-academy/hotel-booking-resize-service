package com.greenfox.malachit.service;

import com.greenfox.malachit.controller.HeartbeatRestController;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

  @Before("execution(public * healthCheck())")
  public void testAdvice() {
    org.slf4j.Logger logger = LoggerFactory.getLogger(HeartbeatRestController.class);
    logger.info("HTTP-REQUEST /heartbeat works as intended");
  }
}
