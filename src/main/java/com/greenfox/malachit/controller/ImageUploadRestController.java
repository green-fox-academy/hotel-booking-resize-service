package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.FileData;
import com.greenfox.malachit.model.ImageData;
import com.greenfox.malachit.model.ImageResponse;
import com.greenfox.malachit.repository.ImageDataReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageUploadRestController {

//  @Autowired ImageDataReposytory imageDataReposytory;

  @PostMapping(value="/images", headers="content-type=multipart/*")
  public ImageResponse getFile(@RequestParam("fileName") MultipartFile file){
    ImageResponse imageResponse = new ImageResponse();
    String fileType = file.getContentType();
    ImageData imageData = new ImageData(file.getName());
    FileData fileData = new FileData(file.getContentType(), imageData);
//    imageDataReposytory.save(imageData);
    imageResponse.setData(fileData);
    return imageResponse;
  }
}
