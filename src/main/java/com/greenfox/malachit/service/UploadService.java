package com.greenfox.malachit.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.greenfox.malachit.model.ThumbnailAttributes;
import com.greenfox.malachit.repository.ImageDataRepository;
import com.greenfox.malachit.repository.ThumbnailRepository;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public class UploadService {

  private static final String bucketName = "hotelbookingproject";

  public void downloadImage(String fileName, ImageDataRepository imageDataRepository, ThumbnailRepository thumbnailRepository) throws Exception {
    URL url = new URL("http://www.mme.hu/sites/default/files/styles/slideshow/public/field/image/tengelic_szaraz_agon_budapest_20140530_orban_zoltan_0001_0.jpg");
    BufferedImage downloadedFile = ImageIO.read(url);
    File toResize = new File(fileName + ".jpg");
    ImageIO.write(downloadedFile, "jpg", toResize);
    resizeImage(toResize, fileName);
    long imageDataId = imageDataRepository.findFirstByUniqueNameEquals(fileName).getId();
    ThumbnailAttributes thumbnailAttributes = thumbnailRepository.findOne(imageDataId);
    thumbnailAttributes.setUploaded(true);
    thumbnailRepository.save(thumbnailAttributes);
  }

  public void uploadImage(File file, String keyName, String bucketName) throws Exception {
    AWSCredentialsProvider awsCredentialsProvider = new EnvironmentVariableCredentialsProvider();
    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.EU_CENTRAL_1)
            .withCredentials(awsCredentialsProvider)
            .build();
    try {
      s3client.putObject(new PutObjectRequest(
              bucketName, keyName +  ".jpeg", file));
    } catch (AmazonServiceException ase) {
      System.out.println(ase.getMessage());
    } catch (AmazonClientException ace) {
      System.out.println(ace.getMessage());
    }
//    file.delete();
  }

  public void resizeImage(File toResize, String uniqueName) throws Exception {
    File resizedFile = new File("thumb" + toResize.getName());
    resizedFile.createNewFile();
    FileOutputStream fos = new FileOutputStream(resizedFile);
    Thumbnails.of(ImageIO.read(toResize)).crop(Positions.CENTER).outputFormat("jpg").size(200,150).keepAspectRatio(true).toOutputStream(fos);
    fos.close();
    uploadImage(resizedFile, uniqueName, bucketName);
  }
}
