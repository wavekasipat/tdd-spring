package com.example.day1.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

  public UserResponse get(int id) {
    if (id == 2) {
      throw new UserNotFoundException(id);
    }
    UserResponse userResponse = new UserResponse();
    userResponse.setId(id);
    userResponse.setFname("John");
    userResponse.setLname("Doe");
    return userResponse;
  }
}
