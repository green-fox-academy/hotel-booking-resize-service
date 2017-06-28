package com.greenfox.malachit.service;

import com.greenfox.malachit.model.*;
import com.greenfox.malachit.repository.ThumbnailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ThumbnailService {
  private final static String HOSTNAMEURL = "https://your-hostname.com/media/images/";
  private FileData fileData;
  private SelfUrl selfUrl;
  private ThumbnailRepository thumbnailRepository;
  private long currentId;

  @Autowired
  public ThumbnailService(ThumbnailRepository thumbnailRepository) {
    this.thumbnailRepository = thumbnailRepository;
  }
  //********************************
  //should get id and isMain boolean
  //********************************
  public ThumbnailResponse createResponse(boolean isMain, long id) {
    thumbnailRepository.save(new ThumbnailAttributes());
    id = thumbnailRepository.findFirstByOrderByIdDesc().getId();
    return new ThumbnailResponse();
  }

  public FileData thumbnailData(boolean isMain) {
    FileData toReturn = new FileData();
    toReturn.setType("thumbnails");
    toReturn.setAttributes(this.saveThumbnailAttributes(isMain));
    toReturn.setId(currentId);
    return toReturn;
  }

  public ThumbnailAttributesDTO saveThumbnailAttributes(boolean isMain) {
    ThumbnailAttributes toSave = new ThumbnailAttributes();
    toSave.setType("thumbnails");
    toSave.set_main(isMain);
    toSave.setCreated_at(LocalDateTime.now().toString());
    toSave.setUploaded(false);
    toSave.setContent_url(this.generateContentUrl());
    toSave.setId(currentId);
    thumbnailRepository.save(toSave);
    return this.createThumbnailDto(toSave);
  }

  public String generateContentUrl() {
    thumbnailRepository.save(new ThumbnailAttributes());
    return HOSTNAMEURL + currentId + "content";
  }

  public String createSelfUrl(long id) {
    return "https://your-hostname.com/hotels/" + id + "/thumbnails/" + currentId;
  }

  public ThumbnailAttributesDTO createThumbnailDto(ThumbnailAttributes thumbnailAttributes) {
    return new ThumbnailAttributesDTO(thumbnailAttributes.is_main(), thumbnailAttributes.isUploaded(), thumbnailAttributes.getCreated_at(), thumbnailAttributes.getContent_url());
  }
}
