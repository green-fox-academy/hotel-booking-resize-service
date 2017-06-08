package com.greenfox.malachit.repository;

import com.greenfox.malachit.model.HealthCheck;
import org.springframework.data.repository.CrudRepository;

public interface HealthCheckRepository extends CrudRepository<HealthCheck, Long>{
}
