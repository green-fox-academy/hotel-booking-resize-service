package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.ImageResponse;
import com.greenfox.malachit.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ThumbnailUploadRestController {

  private ImageService imageService;

  @Autowired
  public ThumbnailUploadRestController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping(value = "/hotels/{imageId}/thumbnail")
  @ResponseBody
  public ImageResponse getfile(@RequestBody ImageResponse imageResponse, @PathVariable long imageId) throws Exception {
    imageResponse.getData().getAttributes();
    System.out.println(imageId);
    return imageResponse;
  }
}
