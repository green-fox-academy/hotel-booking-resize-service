package com.greenfox.malachit.service;

import com.greenfox.malachit.model.*;
import com.greenfox.malachit.repository.ThumbnailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ThumbnailService {
  private final static String HOSTNAMEURL = "https://your-hostname.com/media/images/";
  private ThumbnailRepository thumbnailRepository;

  @Autowired
  public ThumbnailService(ThumbnailRepository thumbnailRepository) {
    this.thumbnailRepository = thumbnailRepository;
  }

  public ThumbnailResponse createResponse(boolean isMain, long hotelId) {
    thumbnailRepository.save(new ThumbnailAttributes());
    long thumbnailId = thumbnailRepository.findFirstByOrderByIdDesc().getId();
    ThumbnailResponse toReturn = new ThumbnailResponse();
    toReturn.setLinks(new SelfUrl(this.createSelfUrl(hotelId, thumbnailId)));
    toReturn.setData(thumbnailData(isMain, hotelId, thumbnailId));
    return toReturn;
  }

  public FileData thumbnailData(boolean isMain, long hotelId, long thumbnailId) {
    FileData toReturn = new FileData();
    toReturn.setType("thumbnails");
    toReturn.setAttributes(this.saveThumbnailAttributes(isMain, hotelId, thumbnailId));
    toReturn.setId(thumbnailId);
    return toReturn;
  }

  public ThumbnailAttributesDTO saveThumbnailAttributes(boolean isMain, long hotelId, long thumbnailId) {
    ThumbnailAttributes toSave = thumbnailRepository.findOne(thumbnailId);
    toSave.setType("thumbnails");
    toSave.setIs_main(isMain);
    toSave.setCreated_at(LocalDateTime.now().toString());
    toSave.setUploaded(false);
    toSave.setContent_url(this.generateContentUrl(thumbnailId));
    toSave.setHotel(hotelId);
    thumbnailRepository.save(toSave);
    return this.createThumbnailDto(toSave);
  }

  public String generateContentUrl(long thumbnailId) {
    return HOSTNAMEURL + thumbnailId + "/content";
  }

  public String createSelfUrl(long id, long thumbnailId) {
    return "https://your-hostname.com/hotels/" + id + "/thumbnails/" + thumbnailId;
  }

  public ThumbnailAttributesDTO createThumbnailDto(ThumbnailAttributes thumbnailAttributes) {
    ThumbnailAttributesDTO toReturn = new ThumbnailAttributesDTO();
    toReturn.setIs_main(thumbnailAttributes.isIs_main());
    toReturn.setContent_url(thumbnailAttributes.getContent_url());
    toReturn.setCreated_at(thumbnailAttributes.getCreated_at());
    toReturn.setUploaded(thumbnailAttributes.isUploaded());
    return toReturn;
  }

  public ThumbnailResponse getListingResponse(long hotelId, Specification<ThumbnailAttributes> specification) {
    return createMainFilteredResponse(hotelId, specification);
  }

//  public List<ThumbnailAttributes> sortWithCustomQuery(Specification<ThumbnailAttributes> specification) {
//    try {
//      return thumbnailRepository.findAll(specification);
//    } catch (Exception e) {
//      System.out.println(e);
//      return null;
//    }
//  }

//  public ThumbnailResponse createListingResponse(long hotelId) {
//    ThumbnailResponse toReturn = new ThumbnailResponse();
//    toReturn.setData(createListingData(hotelId));
//    toReturn.setLinks(new SelfUrl(this.createListingUrl(hotelId)));
//    return toReturn;
//  }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

  public ThumbnailResponse createMainFilteredResponse(long hotelId, Specification<ThumbnailAttributes> specification) {
    ThumbnailResponse toReturn = new ThumbnailResponse();
    toReturn.setData(createFilteredListingData(hotelId, specification));
    toReturn.setLinks(new SelfUrl(this.createFilteredListingUrl(hotelId)));
    return toReturn;
  }

  public ThumbnailResponse createSingleImageResponse(long hotelId, long imageId) throws Exception{
    ThumbnailResponse toReturn = new ThumbnailResponse();
    toReturn.setData(createSingleImageData(hotelId, imageId));
    toReturn.setLinks(new SelfUrl(this.createSingleImageUrl(hotelId, imageId)));
    return toReturn;
  }

  public String createFilteredListingUrl(long hotelId) {
    return "https://your-hostname.com/hotels/" + hotelId + "/thumbnails?is_main=true";
  }

  public String createSingleImageUrl(long hotelId, long imageId) {
    return "https://your-hostname.com/api/hotels/" + hotelId + "/thumbnails/" + imageId;
  }

//  public String createListingUrl(long hotelId) {
//    return "https://your-hostname.com/hotels/" + hotelId + "/thumbnails";
//  }

  //"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
  public List<FileData> createFilteredListingData(long hotelId, Specification<ThumbnailAttributes> specification) {
    List<FileData> toReturn = new ArrayList<>();
    List<ThumbnailAttributes> thumbnails= thumbnailRepository.findAll(specification);
    for (ThumbnailAttributes thumbnailAttributes : thumbnails) {
      if(thumbnailAttributes.getHotel() == hotelId) {
        toReturn.add(createThumbnailListElements(thumbnailAttributes));
      }
    }
    return toReturn;
  }

//  public List<FileData> createListingData(long hotelId) {
//    List<FileData> toReturn = new ArrayList<>();
//    List<ThumbnailAttributes> thumbnails= thumbnailRepository.findAllByHotelEquals(hotelId);
//    for (ThumbnailAttributes thumbnailAttributes : thumbnails) {
//      toReturn.add(createThumbnailListElements(thumbnailAttributes));
//    }
//    return toReturn;
//  }

  public FileData createSingleImageData(long hotelId, long imageId) throws Exception{
    if(thumbnailRepository.exists(imageId)) {
      FileData toReturn = new FileData();
      ThumbnailAttributes buffer = thumbnailRepository.findFirstByIdAndHotelEquals(imageId, hotelId);
      toReturn.setType(buffer.getType());
      toReturn.setId(buffer.getId());
      toReturn.setAttributes(createThumbnailDto(buffer));
      return toReturn;
    }
    else {
      throw new NoImageFoundException(Long.toString(imageId));
    }
  }

  public FileData createThumbnailListElements(ThumbnailAttributes thumbnailAttributes) {
    FileData toReturn = new FileData();
    toReturn.setType(thumbnailAttributes.getType());
    toReturn.setId(thumbnailAttributes.getId());
    toReturn.setAttributes(createThumbnailDto(thumbnailAttributes));
    return toReturn;
  }

  public LinkResponse deleteSingleThumbnail(long hotelId, long thumbnailId) {
    LinkResponse linkResponse = new LinkResponse();
    linkResponse.setLinks(new SelfUrl(this.createSelfUrl(hotelId, thumbnailId)));
    thumbnailRepository.delete(thumbnailId);
    return linkResponse;
  }
}
