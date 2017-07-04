package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.*;
import com.greenfox.malachit.service.ErrorHandlerService;
import com.greenfox.malachit.service.ThumbnailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ThumbnailUploadRestControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          Charset.forName("utf8"));

  @Autowired
  private MockMvc mockMvc;

  @Mock
  ThumbnailService thumbnailService;

  @Mock
  ErrorHandlerService errorHandlerService;

  @InjectMocks
  private ThumbnailUploadRestController thumbnailUploadRestController =
      new ThumbnailUploadRestController(thumbnailService, errorHandlerService);

  private long testHotelId = 6L;
  private long testThumbnailAttributesId = 7L;
  private boolean testIsMain = true;
  private boolean testUploaded = false;
  private String testCreatedAt = "2017-07-03T20:56:53.097";

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(thumbnailUploadRestController).build();
  }

  @Test
  public void addThumbnail() throws Exception {
    String jsonn = "{\"data\": {\"type\": \"thumbnails\",\"attributes\": {\"is_main\": true}}}";
    ThumbnailResponse thumbnailResponse = new ThumbnailResponse();
    thumbnailResponse.setLinks(new SelfUrl("https://your-hostname.com/hotels/6/thumbnails/7"));
    FileData fileData = new FileData();
    fileData.setId(testThumbnailAttributesId);
    fileData.setType("thumbnails");
    ThumbnailAttributesDTO thumbnailAttributesDTO = new ThumbnailAttributesDTO();
    thumbnailAttributesDTO.setContent_url("https://your-hostname.com/media/images/7/content");
    thumbnailAttributesDTO.setCreated_at(testCreatedAt);
    thumbnailAttributesDTO.setIs_main(testIsMain);
    thumbnailAttributesDTO.setUploaded(testUploaded);
    fileData.setAttributes(thumbnailAttributesDTO);
    thumbnailResponse.setData(fileData);
    Mockito.when(thumbnailService.createResponse(testIsMain, testHotelId)).thenReturn(thumbnailResponse);
    mockMvc
        .perform(post("/hotels/{hotelId}/thumbnails", testHotelId)
                .contentType(contentType).content(jsonn))
            .andExpect(status().isCreated())
            .andExpect(content()
                    .json("{\n" +
                            "  \"links\": {\n" +
                            "    \"self\": \"https://your-hostname.com/hotels/6/thumbnails/7\"\n" +
                            "  },\n" +
                            "  \"data\": {\n" +
                            "    \"type\": \"thumbnails\",\n" +
                            "    \"id\": 7,\n" +
                            "    \"attributes\": {\n" +
                            "      \"is_main\": true,\n" +
                            "      \"uploaded\": false,\n" +
                            "      \"created_at\": \"2017-07-03T20:56:53.097\",\n" +
                            "      \"content_url\": \"https://your-hostname.com/media/images/7/content\"\n" +
                            "    }\n" +
                            "  }\n" +
                            "}"));
  }
}