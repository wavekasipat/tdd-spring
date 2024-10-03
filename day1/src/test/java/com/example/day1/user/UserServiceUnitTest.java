package com.example.day1.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

  @Mock
  private UserRepository userRepository;

  @Test
  void get() {
    // Arrange
    MyUser dummy = new MyUser();
    dummy.setId(1L);
    dummy.setFirstName("John");
    dummy.setLastName("Doe");
    when(userRepository.findById(1L)).thenReturn(Optional.of(dummy));
    UserService userService = new UserService(userRepository);

    // Act
    UserResponse userResponse = userService.get(1);

    // Assert
    assertEquals(1, userResponse.getId());
    assertEquals("John", userResponse.getFname());
    assertEquals("Doe", userResponse.getLname());
  }
}
