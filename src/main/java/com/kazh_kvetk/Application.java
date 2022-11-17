package com.kazh_kvetk;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
    builder.run();
  }
}
