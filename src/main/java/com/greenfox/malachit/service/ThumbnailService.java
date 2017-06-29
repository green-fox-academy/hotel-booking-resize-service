package com.greenfox.malachit.service;

import com.greenfox.malachit.model.*;
import com.greenfox.malachit.repository.ThumbnailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ThumbnailService {
  private final static String HOSTNAMEURL = "https://your-hostname.com/media/images/";
  private ThumbnailRepository thumbnailRepository;
  private long currentId;

  @Autowired
  public ThumbnailService(ThumbnailRepository thumbnailRepository) {
    this.thumbnailRepository = thumbnailRepository;
  }

  public ThumbnailResponse createResponse(boolean isMain, long hotelId) {
    thumbnailRepository.save(new ThumbnailAttributes());
    currentId = thumbnailRepository.findFirstByOrderByIdDesc().getId();
    ThumbnailResponse toReturn = new ThumbnailResponse();
    toReturn.setLinks(new SelfUrl(this.createSelfUrl(hotelId)));
    toReturn.setData(thumbnailData(isMain, hotelId));
    return toReturn;
  }

  public FileData thumbnailData(boolean isMain, long hotelId) {
    FileData toReturn = new FileData();
    toReturn.setType("thumbnails");
    toReturn.setAttributes(this.saveThumbnailAttributes(isMain, hotelId));
    toReturn.setId(currentId);
    return toReturn;
  }

  public ThumbnailAttributesDTO saveThumbnailAttributes(boolean isMain, long hotelId) {
    ThumbnailAttributes toSave = thumbnailRepository.findOne(currentId);
    toSave.setType("thumbnails");
    toSave.setIs_main(isMain);
    toSave.setCreated_at(LocalDateTime.now().toString());
    toSave.setUploaded(false);
    toSave.setContent_url(this.generateContentUrl());
    toSave.setHotel(hotelId);
    thumbnailRepository.save(toSave);
    return this.createThumbnailDto(toSave);
  }

  public String generateContentUrl() {
    return HOSTNAMEURL + currentId + "/content";
  }

  public String createSelfUrl(long id) {
    return "https://your-hostname.com/hotels/" + id + "/thumbnails/" + currentId;
  }

  public ThumbnailAttributesDTO createThumbnailDto(ThumbnailAttributes thumbnailAttributes) {
    ThumbnailAttributesDTO toReturn = new ThumbnailAttributesDTO();
    toReturn.setIs_main(thumbnailAttributes.isIs_main());
    toReturn.setContent_url(thumbnailAttributes.getContent_url());
    toReturn.setCreated_at(thumbnailAttributes.getCreated_at());
    toReturn.setUploaded(thumbnailAttributes.isUploaded());
    return toReturn;
  }

  public List<FileData> createListingResponse(long hotelId) {
    List<FileData> toReturn = new ArrayList<>();
    List<ThumbnailAttributes> thumbnails= thumbnailRepository.findAllByHotelEquals(hotelId);
    for (ThumbnailAttributes thumbnailAttributes : thumbnails) {
      toReturn.add(createThumbnailListElements(thumbnailAttributes));
    }
    return toReturn;
  }

  public FileData createThumbnailListElements(ThumbnailAttributes thumbnailAttributes) {
    FileData toReturn = new FileData();
    toReturn.setType(thumbnailAttributes.getType());
    toReturn.setId(thumbnailAttributes.getId());
    toReturn.setAttributes(createThumbnailDto(thumbnailAttributes));
    return toReturn;
  }
}
