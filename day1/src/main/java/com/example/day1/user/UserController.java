package com.example.day1.user;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user/{id}")
  public UserResponse getById(@PathVariable int id) {
    return userService.get(id);
  }

  @PostMapping("/user")
  public UserResponse createNewUser(
    @Valid @RequestBody UserRequest userRequest
  ) {
    System.out.println(userRequest);
    return userService.create(userRequest);
  }
}
