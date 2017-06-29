package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.IncomingDataMap;
import com.greenfox.malachit.model.NullPointerResponse;
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

  @ExceptionHandler(java.lang.Exception.class)
  public NullPointerResponse nullpointerErrorHandling(long imageId) {
    return thumbnailService.nullPointerResponse(imageId);
  }

  @PostMapping("/hotels/{hotelId}/thumbnails")
  @ResponseStatus(HttpStatus.CREATED)
  public ThumbnailResponse addThumbnail(@RequestBody IncomingDataMap incomingDataMap, @PathVariable long hotelId) throws Exception {
    return thumbnailService.createResponse(incomingDataMap.getData().getAttributes().getIs_main(), hotelId);
  }

  @GetMapping("/hotels/{hotelId}/thumbnails")
  @ResponseStatus(HttpStatus.OK)
  public ThumbnailResponse thumbnailFilteredMainListing(@PathVariable long hotelId, @RequestParam(value = "is_main", defaultValue = "false") boolean is_main) {
    return thumbnailService.getListingResponse(hotelId, is_main);
  }

  @GetMapping("/hotels/{hotelId}/thumbnails/{imageId}")
  @ResponseStatus(HttpStatus.OK)
  public ThumbnailResponse thumbnailSingleTumbnail(@PathVariable long hotelId, @PathVariable long imageId) {
    return thumbnailService.createSingleImageResponse(hotelId, imageId);
  }
}
