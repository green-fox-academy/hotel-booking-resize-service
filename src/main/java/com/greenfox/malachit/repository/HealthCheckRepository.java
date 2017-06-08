package com.greenfox.malachit.repository;

import com.greenfox.malachit.model.HealthCheck;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface HealthCheckRepository extends CrudRepository<HealthCheck, Long>{
  public ArrayList<HealthCheck> findAllByOrderById();
}
