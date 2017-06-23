package com.greenfox.malachit.service;

import com.greenfox.malachit.model.FileData;
import com.greenfox.malachit.model.ImageData;
import com.greenfox.malachit.model.ImageResponse;
import com.greenfox.malachit.repository.ImageDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

  private ImageDataRepository imageDataRepository;
  private CheckTypeService checkTypeService;

  @Autowired
  public ImageService(ImageDataRepository imageDataRepository, CheckTypeService checkTypeService) {
    this.imageDataRepository = imageDataRepository;
    this.checkTypeService = checkTypeService;
  }

  public ImageResponse createResponse(MultipartFile file) {
    ImageResponse imageResponse = new ImageResponse();
    ImageData imageData = new ImageData(file.getOriginalFilename());
    FileData fileData = new FileData(checkTypeService.checkIfImage(file.getContentType()), imageData);
    imageDataRepository.save(imageData);
    imageResponse.setData(fileData);
    return imageResponse;
  }
}
