package com.greenfox.malachit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventCreatorTest {

  @Test
  public void EventCreatorTest() {

    EventCreator eventCreatorUnderTest = new EventCreator("testMessage");
    assertEquals("testMessage", eventCreatorUnderTest.getMessage());
  }
}
