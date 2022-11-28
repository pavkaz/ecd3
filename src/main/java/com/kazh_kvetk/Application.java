package com.kazh_kvetk;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
    builder.run();
  }
}
