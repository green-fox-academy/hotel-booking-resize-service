package com.greenfox.malachit.service;

import com.greenfox.malachit.model.ImageData;
import com.greenfox.malachit.repository.ImageDataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UniqueNameTest {


  @Test
  public void createUniqueName() throws Exception {
    ArrayList<ImageData> mockedImageDataArrayList = new ArrayList<>();
    mockedImageDataArrayList.add(new ImageData("s3-eu-west-1.amazonaws.com/somebucket/hj2rtk4ds7pl.jpg"));
    ImageDataRepository mockedImageDataRepository = Mockito.mock(ImageDataRepository.class);
    Mockito.when(mockedImageDataRepository.findAll()).thenReturn(mockedImageDataArrayList);
    UniqueName uniqueNameTested = new UniqueName(mockedImageDataRepository);
    String mockedImageName = "hj2rtk4ds7pl";
    assertFalse(uniqueNameTested.createUniqueName().equals(mockedImageName));
  }
}