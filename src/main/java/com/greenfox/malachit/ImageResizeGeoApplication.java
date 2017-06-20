package com.greenfox.malachit;

import com.greenfox.malachit.model.HealthCheck;
import com.greenfox.malachit.repository.HealthCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageResizeGeoApplication implements CommandLineRunner{

  @Autowired
  HealthCheckRepository healthCheckRepository;

	public static void main(String[] args) {
		SpringApplication.run(ImageResizeGeoApplication.class, args);
	}

  @Override
  public void run(String... args) throws Exception {
  }
}
