package com.example.day1.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.day1.global.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerFailureTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void getByIdNotFound() {
    ResponseEntity<ErrorResponse> errorResponse = restTemplate.getForEntity(
      "/user/2",
      ErrorResponse.class
    );
    assertEquals(HttpStatus.NOT_FOUND, errorResponse.getStatusCode());
    assertEquals("User id 2 not found", errorResponse.getBody().getMessage());
  }

  @Test
  void getByIdInvalid() {
    ResponseEntity<ErrorResponse> errorResponse = restTemplate.getForEntity(
      "/user/invalid",
      ErrorResponse.class
    );
    assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusCode());
    assertEquals(
      "Failed to convert value of type 'java.lang.String' to required type 'int'; For input string: \"invalid\"",
      errorResponse.getBody().getMessage()
    );
  }
}
