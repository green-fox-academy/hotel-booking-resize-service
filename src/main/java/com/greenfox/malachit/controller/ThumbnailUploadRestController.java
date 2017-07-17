package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.*;
import com.greenfox.malachit.service.ErrorHandlerService;
import com.greenfox.malachit.service.ParamToUserSpecService;
import com.greenfox.malachit.service.ThumbnailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ThumbnailUploadRestController {

  private ThumbnailService thumbnailService;
  private ErrorHandlerService errorHandlerService;

  @Autowired
  ParamToUserSpecService paramToUserSpecService;

  @Autowired
  public ThumbnailUploadRestController(ThumbnailService thumbnailService, ErrorHandlerService errorHandlerService) {
    this.errorHandlerService = errorHandlerService;
    this.thumbnailService = thumbnailService;
  }

  @ExceptionHandler(NoImageFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, Object> handleMissingParam(NoImageFoundException e) {
    return errorHandlerService.getResponse(e, "404", "not found");
  }

  @PostMapping("/hotels/{hotelId}/thumbnails")
  @ResponseStatus(HttpStatus.CREATED)
  public ThumbnailResponse addThumbnail(@RequestBody IncomingDataMap incomingDataMap, @PathVariable long hotelId) throws Exception {
    return thumbnailService.createResponse(incomingDataMap.getData().getAttributes().getIs_main(), hotelId);
  }

  @GetMapping("/hotels/{hotelId}/thumbnails")
  @ResponseStatus(HttpStatus.OK)
  public ThumbnailResponse thumbnailFilteredMainListing(@PathVariable long hotelId, @RequestParam LinkedHashMap<String, Object> allRequestParams,
                                                        HttpServletRequest request) {
    return thumbnailService.getListingResponse(hotelId, paramToUserSpecService.getParameters(allRequestParams), request);
  }

  @GetMapping("/hotels/{hotelId}/thumbnails/{imageId}")
  @ResponseStatus(HttpStatus.OK)
  public ThumbnailResponse thumbnailSingleThumbnail(@PathVariable long hotelId, @PathVariable long imageId) throws Exception {
    return thumbnailService.createSingleImageResponse(hotelId, imageId);
  }

  @DeleteMapping("/hotels/{hotelId}/thumbnails/{thumbnailId}")
  @ResponseStatus(HttpStatus.OK)
  public LinkResponse deleteSingleThumbnail(@PathVariable long hotelId, @PathVariable long thumbnailId) {
    return thumbnailService.deleteSingleThumbnail(hotelId, thumbnailId);
  }
}
