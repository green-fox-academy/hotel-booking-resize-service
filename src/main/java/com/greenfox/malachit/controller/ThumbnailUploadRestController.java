package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.ImageResponse;
import com.greenfox.malachit.model.IncomingDataMap;
import com.greenfox.malachit.model.ThumbnailResponse;
import com.greenfox.malachit.service.ImageService;
import com.greenfox.malachit.service.ThumbnailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ThumbnailUploadRestController {

  private ThumbnailService thumbnailService;

  @Autowired
  public ThumbnailUploadRestController(ThumbnailService thumbnailService) {
    this.thumbnailService = thumbnailService;
  }

  @PostMapping(value = "/hotels/{hotelId}/thumbnail")
  @ResponseBody
  public ThumbnailResponse getfile(@RequestBody IncomingDataMap incomingDataMap, @PathVariable long hotelId) throws Exception {
    return thumbnailService.createResponse(incomingDataMap.getData().getAttributes().is_main(), hotelId);
  }
}
