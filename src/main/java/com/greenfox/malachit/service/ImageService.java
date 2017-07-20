package com.greenfox.malachit.service;

import com.greenfox.malachit.model.*;
import com.greenfox.malachit.repository.ImageDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageService {

  private static final String bucketName = "hotelbookingproject";
//  private static final String  BUCKETURL = "https://s3.eu-central-1.amazonaws.com/hotelbookingproject/";
  private ImageDataRepository imageDataRepository;
  private CheckTypeService checkTypeService;

  @Autowired
  public ImageService(ImageDataRepository imageDataRepository, CheckTypeService checkTypeService) {
    this.imageDataRepository = imageDataRepository;
    this.checkTypeService = checkTypeService;
  }

  public ImageResponse createResponse(MultipartFile file, long id) throws Exception {
    validateImage(file);
    String uniqueName = new UniqueName(imageDataRepository).createUniqueName();
    uploadImage(file, uniqueName);
    String imageThumbnailUrl = "/media/images/" + id + "/resize/200/150";
    ImageResponse imageResponse = new ImageResponse();
    ImageData imageData = new ImageData(id, uniqueName);
    ImageDataDTO imageDataDTO = new ImageDataDTO(id, imageThumbnailUrl);
    FileDataDTO fileData = new FileDataDTO(checkTypeService.checkIfImage(file.getContentType()), imageDataDTO);
    imageDataRepository.save(imageData);
    imageResponse.setData(fileData);
    return imageResponse;
  }

  public void validateImage(MultipartFile toValidate) throws IOException {
    if (!checkTypeService.checkIfValidExtension(toValidate.getContentType())) {
      throw new ImageExtensionNotValidException();
    }
    BufferedImage bufferedImage = ImageIO.read(toValidate.getInputStream());
    int height = bufferedImage.getHeight();
    int width = bufferedImage.getWidth();
    if (height < 150 || width < 200) {
      throw new ImageDimensionTooSmallException();
    }
  }

  public void uploadImage(MultipartFile file, String keyName) throws Exception {
    UploadService uploadService = new UploadService();
    uploadService.uploadImage(convert(file), keyName, bucketName);
  }

  public File convert(MultipartFile file) throws Exception {
    File convertedFile = new File(file.getOriginalFilename());
    convertedFile.createNewFile();
    FileOutputStream fos = new FileOutputStream(convertedFile);
    fos.write(file.getBytes());
    fos.close();
    return convertedFile;
  }

  public void checkFileSize(MultipartFile checkSizeOf) {
    if (checkSizeOf.getSize() > 1000000) {
      throw new FileTooLargeException();
    }
  }
}
