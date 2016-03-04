package com.missedfaces.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = "classpath*:spring/integration.xml")
public class MissedFacesServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(MissedFacesServerApplication.class, args);
  }
}
