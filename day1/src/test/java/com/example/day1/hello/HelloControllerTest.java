package com.example.day1.hello;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HelloControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void sayHello() {
    HelloResponse result = restTemplate.getForObject(
      "/hello",
      HelloResponse.class
    );
    assertEquals("Hello World", result.getMessage());
  }
}
