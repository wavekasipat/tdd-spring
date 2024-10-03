package com.example.day1.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class UserRepositoryMySQLTest {

  @Container
  @ServiceConnection
  static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql");

  @DynamicPropertySource
  static void mysqlProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", () -> mySQLContainer.getJdbcUrl());
    registry.add(
      "spring.datasource.driverClassName",
      () -> mySQLContainer.getDriverClassName()
    );
    registry.add(
      "spring.datasource.username",
      () -> mySQLContainer.getUsername()
    );
    registry.add(
      "spring.datasource.password",
      () -> mySQLContainer.getPassword()
    );
    registry.add("spring.sql.init.mode", () -> "always");
  }

  @Autowired
  private UserRepository userRepository;

//  @Test
  public void case01() {
    // Arrange
    MyUser dummy = new MyUser();
    dummy.setId(1L);
    dummy.setFirstName("Somkiat");
    dummy.setLastName("Pui");
    userRepository.saveAndFlush(dummy);
    // Act
    Optional<MyUser> result = userRepository.findById(1L);
    // Assert
    assertEquals(1, result.get().getId());
    assertEquals("Somkiat", result.get().getFirstName());
  }

//  @Test
  public void case02() {
    // Arrange

    // Act
    Optional<MyUser> result = userRepository.findById(2L);

    // Assert
    assertTrue(result.isEmpty());
  }
}
