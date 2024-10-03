package com.example.day1.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.day1.global.ErrorResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerFailureMockTest {

  @MockBean
  private UserService userService;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("Failure - Mock - Get user by id 2 - return 404 not found")
  void getByIdNotFound() {
    // Arrange
    when(userService.get(2)).thenThrow(new UserNotFoundException(2));
    // Act
    ResponseEntity<ErrorResponse> errorResponse = restTemplate.getForEntity(
      "/user/2",
      ErrorResponse.class
    );
    // Assert
    assertEquals(HttpStatus.NOT_FOUND, errorResponse.getStatusCode());
    assertEquals("User id 2 not found", errorResponse.getBody().getMessage());
  }
}
