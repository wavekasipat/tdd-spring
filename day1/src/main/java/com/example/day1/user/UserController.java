package com.example.day1.user;

import com.example.day1.post.PostGateway;
import com.example.day1.post.PostResponse;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;
  private final PostGateway postGateway;

  public UserController(UserService userService, PostGateway postGateway) {
    this.userService = userService;
    this.postGateway = postGateway;
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

  @GetMapping("/post/{id}")
  public UserResponse sampleGetPost(@PathVariable int id) {
    Optional<PostResponse> postResponse = postGateway.getById(id);
    if (postResponse.isEmpty()) {
      // empty case
    }
    UserResponse userResponse = new UserResponse();
    userResponse.setId(postResponse.get().getId());
    userResponse.setFname(postResponse.get().getTitle().toUpperCase());
    return userResponse;
  }
}
