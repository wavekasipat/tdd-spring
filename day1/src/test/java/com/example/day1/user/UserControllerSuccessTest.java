package com.example.day1.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerSuccessTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("Sucess - Get User by Id")
  void getById() {
    UserResponse userResponse = restTemplate.getForObject(
      "/user/1",
      UserResponse.class
    );
    assertEquals("John", userResponse.getFname());
    assertEquals("Doe", userResponse.getLname());
    assertEquals(1, userResponse.getId());
  }
}
