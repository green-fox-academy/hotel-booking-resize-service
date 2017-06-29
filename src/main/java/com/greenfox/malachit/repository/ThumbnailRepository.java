package com.greenfox.malachit.repository;

import com.greenfox.malachit.model.ThumbnailAttributes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThumbnailRepository extends CrudRepository<ThumbnailAttributes, Long>{
  public ThumbnailAttributes findFirstByOrderByIdDesc();
  public List<ThumbnailAttributes> findAllByHotelEquals(long hotel);
}
