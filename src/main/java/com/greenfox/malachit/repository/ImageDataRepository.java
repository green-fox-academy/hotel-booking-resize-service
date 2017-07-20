package com.greenfox.malachit.repository;

import com.greenfox.malachit.model.ImageData;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ImageDataRepository extends CrudRepository<ImageData, Long> {
  public ImageData findFirstByUniqueNameEquals(String uniqueName);

  @Override
  public ArrayList<ImageData> findAll();
}
