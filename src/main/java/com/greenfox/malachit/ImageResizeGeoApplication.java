package com.greenfox.malachit;

import com.greenfox.malachit.repository.HealthCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageResizeGeoApplication {

  @Autowired
  HealthCheckRepository healthCheckRepository;

	public static void main(String[] args) {
		SpringApplication.run(ImageResizeGeoApplication.class, args);
	}
}
