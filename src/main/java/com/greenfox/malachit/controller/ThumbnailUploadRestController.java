package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.*;
import com.greenfox.malachit.service.ThumbnailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ThumbnailUploadRestController {

  private ThumbnailService thumbnailService;

  @Autowired
  public ThumbnailUploadRestController(ThumbnailService thumbnailService) {
    this.thumbnailService = thumbnailService;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, Object> handleMissingParam(NoImageFoundException e) {

    Map<String, Object> result = new HashMap();
    List<ErrorReturnObject> errors = new ArrayList<>();

    errors.add(new ErrorReturnObject("404", "not found", e.getMessage()));
    result.put("errors", errors);
    return result;
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
  public ThumbnailResponse thumbnailSingleTumbnail(@PathVariable long hotelId, @PathVariable long imageId) throws Exception {
    return thumbnailService.createSingleImageResponse(hotelId, imageId);
  }

  @DeleteMapping("/hotels/{hotelId}/thumbnails/{thumbnailId}")
  @ResponseStatus(HttpStatus.OK)
  public LinkResponse deleteSingleThumbnail(@PathVariable long hotelId, @PathVariable long thumbnailId) {
    return thumbnailService.deleteSingleThumbnail(hotelId, thumbnailId);
  }
}
