package com.greenfox.malachit.service;

import com.greenfox.malachit.model.ThumbnailAttributes;
import com.greenfox.malachit.model.ThumbnailAttributesDTO;
import com.greenfox.malachit.repository.ThumbnailRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ThumbnailServiceTestCreateThumbnail {

  long testThumbnailAttributesId = 7L;
  long testHotelId = 2L;
  ThumbnailRepository thumbnailRepository;
  ThumbnailService thumbnailService;

  @Before
  public void setUp() throws Exception {
    thumbnailRepository = Mockito.mock(ThumbnailRepository.class);
    thumbnailService = new ThumbnailService(thumbnailRepository);
    ThumbnailAttributes thumbnailAttributes = new ThumbnailAttributes();
    thumbnailAttributes.setId(testThumbnailAttributesId);
    Mockito.when(thumbnailRepository.findOne(testThumbnailAttributesId)).thenReturn(thumbnailAttributes);
  }

  @Test
  public void createSingleImageDataImageIdExist() throws Exception {
    ThumbnailAttributes thumbnailAttributes = new ThumbnailAttributes();
    thumbnailAttributes.setId(testThumbnailAttributesId);
    thumbnailAttributes.setType("thumbnails");
    thumbnailAttributes.setIs_main(true);
    thumbnailAttributes.setUploaded(false);
    thumbnailAttributes.setCreated_at("2017-07-03T20:56:53.097");
    thumbnailAttributes.setContent_url("https://your-hostname.com/media/images/7/content");
    Mockito.when(thumbnailRepository.exists(testThumbnailAttributesId)).thenReturn(true);
    Mockito.when(thumbnailRepository.findFirstByIdAndHotelEquals
            (testThumbnailAttributesId, testHotelId)).thenReturn(thumbnailAttributes);
    assertEquals(thumbnailService.createSingleImageData(testHotelId, testThumbnailAttributesId).getType(), "thumbnails");
    assertEquals(thumbnailService.createSingleImageData(testHotelId, testThumbnailAttributesId).getId(), 7L);
    ThumbnailAttributesDTO testedThumbnailAttributesDTO = (ThumbnailAttributesDTO)
        thumbnailService
            .createSingleImageData(testHotelId, testThumbnailAttributesId)
            .getAttributes();
    assertTrue(testedThumbnailAttributesDTO.isIs_main());
    assertFalse(testedThumbnailAttributesDTO.isUploaded());
  }

  @Test
  public void createThumbnailDto() throws Exception {
    ThumbnailAttributes thumbnailAttributes = new ThumbnailAttributes();
    thumbnailAttributes.setId(testThumbnailAttributesId);
    thumbnailAttributes.setHotel(testHotelId);
    thumbnailAttributes.setType("thumbnails");
    thumbnailAttributes.setIs_main(true);
    thumbnailAttributes.setUploaded(false);
    thumbnailAttributes.setCreated_at("2017-06-26T14:05:10.333");
    thumbnailAttributes.setContent_url("https://your-hostname.com/media/images/2/content");
    assertTrue(thumbnailService.createThumbnailDto(thumbnailAttributes).getContent_url()
            == "https://your-hostname.com/media/images/2/content");
    assertTrue(thumbnailService.createThumbnailDto(thumbnailAttributes).getCreated_at()
            == "2017-06-26T14:05:10.333");
    assertTrue(thumbnailService.createThumbnailDto(thumbnailAttributes).isIs_main());
    assertTrue(!thumbnailService.createThumbnailDto(thumbnailAttributes).isUploaded());
  }

  @Test
  public void saveThumbnailAttributes() throws Exception {
    assertTrue(thumbnailService.saveThumbnailAttributes(true, testHotelId, testThumbnailAttributesId).isIs_main());
    assertEquals(thumbnailService.saveThumbnailAttributes(true, testHotelId, testThumbnailAttributesId)
            .getContent_url(), "https://your-hostname.com/media/images/7/content");
    assertFalse(thumbnailService.saveThumbnailAttributes(true, testHotelId, testThumbnailAttributesId).isUploaded());
  }
}