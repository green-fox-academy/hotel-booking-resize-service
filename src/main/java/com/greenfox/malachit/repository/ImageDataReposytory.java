package com.greenfox.malachit.repository;

import com.greenfox.malachit.model.ImageData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageDataReposytory extends CrudRepository<ImageData, Long> {

  @Override
  public List<ImageData> findAll();
}
