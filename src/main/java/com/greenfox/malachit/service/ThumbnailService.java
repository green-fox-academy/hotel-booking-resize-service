package com.greenfox.malachit.service;

import com.greenfox.malachit.model.FileData;
import com.greenfox.malachit.model.SelfUrl;
import com.greenfox.malachit.model.ThumbnailResponse;

public class ThumbnailService {
  private FileData fileData;
  private SelfUrl selfUrl;

  public ThumbnailResponse createResponse() {
    return new ThumbnailResponse();
  }
}
