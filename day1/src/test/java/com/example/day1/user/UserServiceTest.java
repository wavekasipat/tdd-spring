package com.example.day1.user;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserServiceTest {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("Success - get UserService")
  void get() {
    // Arrange
    MyUser dummy = new MyUser();
    dummy.setId(1L);
    dummy.setFirstName("John");
    dummy.setLastName("Doe");
    userRepository.saveAndFlush(dummy);

    // Act
    UserResponse userResponse = userService.get(1);

    // Assert
    assertEquals(1, userResponse.getId());
    assertEquals("John", userResponse.getFname());
    assertEquals("Doe", userResponse.getLname());
  }

  @Test
  @DisplayName("Failure - get UserService - user not found")
  void getUserNotFound() {
    // Act
    Exception exception = assertThrows(
      UserNotFoundException.class,
      () -> {
        userService.get(99);
      }
    );
    // Assert
    assertEquals("User id 99 not found", exception.getMessage());
  }
}
