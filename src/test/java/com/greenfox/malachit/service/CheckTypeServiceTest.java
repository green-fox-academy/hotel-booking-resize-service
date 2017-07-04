package com.greenfox.malachit.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckTypeServiceTest {
  @Test
  public void checkIfImageIncomingImage() throws Exception {
    String contentType = "image/jpeg";
    CheckTypeService checkTypeService = new CheckTypeService();
    assertEquals(checkTypeService.checkIfImage(contentType), "image");
  }

  @Test
  public void checkIfImageIncomingPdf() throws Exception {
    String contentType = "application/pdf";
    CheckTypeService checkTypeService = new CheckTypeService();
    assertEquals(checkTypeService.checkIfImage(contentType), "application/pdf");
  }
}