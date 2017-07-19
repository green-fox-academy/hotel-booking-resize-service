package com.greenfox.malachit.controller;

import com.greenfox.malachit.model.FileTooLargeException;
import com.greenfox.malachit.model.ImageDimensionTooSmallException;
import com.greenfox.malachit.model.ImageExtensionNotValidException;
import com.greenfox.malachit.model.ImageResponse;
import com.greenfox.malachit.service.ErrorHandlerService;
import com.greenfox.malachit.service.ImageService;
import com.greenfox.malachit.service.MessageQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class ImageUploadRestController {

  private ImageService imageService;
  private ErrorHandlerService errorHandlerService;
  private MessageQueueService messageQueueService;

  @Autowired
  public ImageUploadRestController(ImageService imageService, ErrorHandlerService errorHandlerService, MessageQueueService messageQueueService) {
    this.imageService = imageService;
    this.errorHandlerService = errorHandlerService;
    this.messageQueueService = messageQueueService;
  }

  @ExceptionHandler(FileTooLargeException.class)
  @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
  public Map<String, Object> handleFileTooLarge(FileTooLargeException e) {
    return errorHandlerService.getResponse(e, "413", "The image cannot be bigger than 1MB");
  }

  @ExceptionHandler(ImageDimensionTooSmallException.class)
  @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
  public Map<String, Object> handleImageDimensionTooSmall(ImageDimensionTooSmallException e) {
    return errorHandlerService.getResponse(e, "413", "Not Acceptable");
  }

  @ExceptionHandler(ImageExtensionNotValidException.class)
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public Map<String, Object> handleExtensionNotSupported(ImageExtensionNotValidException e) {
    return errorHandlerService.getResponse(e, "406", "Not Acceptable");
  }

  @PostMapping(value="/media/images/{id}", headers="content-type=multipart/*")
  public ImageResponse getFile(@RequestParam("fileName") MultipartFile file, @PathVariable long id) throws Exception{
    return imageService.createResponse(file, id);
  }

  @GetMapping("/in")
  public String sendToTheQueue() throws Exception {
    messageQueueService.sendTask("valami");
    return "ok";
  }

  @GetMapping("/out")
  public String receiveTusk() throws Exception {
    messageQueueService.receiveTusk();
    return "ok";
  }
}
