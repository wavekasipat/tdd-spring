package com.example.day1.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public HelloResponse sayHello() {
    return new HelloResponse("Hello World");
  }
}
