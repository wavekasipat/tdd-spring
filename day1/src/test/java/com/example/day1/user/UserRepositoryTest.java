package com.example.day1.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("Found user id 1")
  public void foundData() {
    // Arrange
    MyUser dummy = new MyUser();
    dummy.setId(1L);
    dummy.setFirstName("John");
    dummy.setLastName("Doe");
    userRepository.saveAndFlush(dummy);

    // Act
    Optional<MyUser> user = userRepository.findById(1L);

    // Assert
    assertEquals(1, user.get().getId());
    assertEquals("John", user.get().getFirstName());
  }
}
