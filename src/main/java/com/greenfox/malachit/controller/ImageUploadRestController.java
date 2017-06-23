package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.FileData;
import com.greenfox.malachit.model.ImageData;
import com.greenfox.malachit.model.ImageResponse;
import com.greenfox.malachit.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageUploadRestController {

  private ImageService imageService;

  @Autowired
  public ImageUploadRestController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping(value="/images", headers="content-type=multipart/*")
  public ImageResponse getFile(@RequestParam("fileName") MultipartFile file){
    return  imageService.createResponse(file);
  }
}
