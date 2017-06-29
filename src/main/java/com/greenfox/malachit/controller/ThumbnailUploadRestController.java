package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.IncomingDataMap;
import com.greenfox.malachit.model.ThumbnailResponse;
import com.greenfox.malachit.service.ThumbnailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ThumbnailUploadRestController {

  private ThumbnailService thumbnailService;

  @Autowired
  public ThumbnailUploadRestController(ThumbnailService thumbnailService) {
    this.thumbnailService = thumbnailService;
  }

  @PostMapping(value = "/hotels/{hotelId}/thumbnails")
  @ResponseStatus(HttpStatus.CREATED)
  public ThumbnailResponse addThumbnail(@RequestBody IncomingDataMap incomingDataMap, @PathVariable long hotelId) throws Exception {
    return thumbnailService.createResponse(incomingDataMap.getData().getAttributes().getIs_main(), hotelId);
  }

  @GetMapping("/hotels/{hotelId}/thumbnails?is_main=true")
  @ResponseStatus(HttpStatus.OK)
  public ThumbnailResponse thumbnailFilteredMainListing(@PathVariable long hotelId, @RequestParam("is_main") boolean is_main) {
    return thumbnailService.createMainFilteredResponse(hotelId);
  }

  @GetMapping("/hotels/{hotelId}/thumbnails")
  @ResponseStatus(HttpStatus.CREATED)
  public ThumbnailResponse thumbnailListing(@PathVariable long hotelId) {
    return thumbnailService.createListingResponse(hotelId);
  }
}
