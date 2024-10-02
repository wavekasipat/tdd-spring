package com.example.day1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Day1Application {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(
      Day1Application.class,
      args
    );
    System.out.println(context.getBeanDefinitionCount());
  }
}
