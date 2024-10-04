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

  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("Success - Get User by Id")
  void getById() {
    // Arrange
    MyUser dummy = new MyUser();
    dummy.setId(1L);
    dummy.setFirstName("John");
    dummy.setLastName("Doe");
    userRepository.saveAndFlush(dummy);

    // Act
    UserResponse userResponse = restTemplate.getForObject(
      "/user/1",
      UserResponse.class
    );

    // Assert
    assertEquals("John", userResponse.getFname());
    assertEquals("Doe", userResponse.getLname());
    assertEquals(1, userResponse.getId());
  }

  @Test
  @DisplayName("Success - Create User")
  void createUser() {
    // Arrange
    UserRequest userRequest = new UserRequest();
    userRequest.setFname("Jane");
    userRequest.setLname("Doe");
    userRequest.setAge(30);

    // Act
    UserResponse userResponse = restTemplate.postForObject(
      "/user",
      userRequest,
      UserResponse.class
    );

    // Assert
    assertEquals(2, userResponse.getId());
    assertEquals("Jane", userResponse.getFname());
    assertEquals("Doe", userResponse.getLname());
    assertEquals(30, userResponse.getAge());
  }
}
