package com.greenfox.malachit.service;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.greenfox.malachit.model.FileData;
import com.greenfox.malachit.model.FileDataDTO;
import com.greenfox.malachit.model.ImageData;
import com.greenfox.malachit.model.ImageResponse;
import com.greenfox.malachit.repository.ImageDataRepository;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageService {

  private static final String bucketName = "hotelbookingproject";
  private static final String  BUCKETURL = "https://s3.eu-central-1.amazonaws.com/hotelbookingproject/";
  private ImageDataRepository imageDataRepository;
  private CheckTypeService checkTypeService;

  @Autowired
  public ImageService(ImageDataRepository imageDataRepository, CheckTypeService checkTypeService) {
    this.imageDataRepository = imageDataRepository;
    this.checkTypeService = checkTypeService;
  }

  public ImageResponse createResponse(MultipartFile file) throws Exception {
    String imageDataUrl = uploadImage(file);
    ImageResponse imageResponse = new ImageResponse();
    ImageData imageData = new ImageData(imageDataUrl);
    FileDataDTO fileData = new FileDataDTO(checkTypeService.checkIfImage(file.getContentType()), imageData);
    imageDataRepository.save(imageData);
    imageResponse.setData(fileData);
    return imageResponse;
  }

  public String uploadImage(MultipartFile file) throws Exception {
    AmazonS3 s3client = new AmazonS3Client(new EnvironmentVariableCredentialsProvider());
    UniqueName uniqueName = new UniqueName(imageDataRepository);
    String keyName = uniqueName.createUniqueName() + ".jpeg";
    File convertedFile = convert(file);
    try {
      s3client.putObject(new PutObjectRequest(
              bucketName, keyName, convertedFile));
    } catch (AmazonServiceException ase) {
      System.out.println(ase.getMessage());
    } catch (AmazonClientException ace) {
      System.out.println(ace.getMessage());
    }
    convertedFile.delete();
    return BUCKETURL + keyName;
  }

  public File convert(MultipartFile file) throws Exception {
    File convertedFile = new File(file.getOriginalFilename());
    convertedFile.createNewFile();
    FileOutputStream fos = new FileOutputStream(convertedFile);
    fos.write(file.getBytes());
    fos.close();
    return convertedFile;
  }

  public File resizeImage(MultipartFile toResize) throws IOException {
    File resizedFile = new File(toResize.getOriginalFilename());
    resizedFile.createNewFile();
    FileOutputStream fos = new FileOutputStream(resizedFile);
    Thumbnails.of(ImageIO.read(toResize.getInputStream())).crop(Positions.CENTER).size(200,150).keepAspectRatio(true).toOutputStream(fos);
    fos.close();
    return resizedFile;
  }
}
