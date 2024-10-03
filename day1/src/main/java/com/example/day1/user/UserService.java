package com.example.day1.user;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserResponse get(int id) {
    Optional<MyUser> user = userRepository.findById((long) id);
    if (user.isEmpty()) {
      throw new UserNotFoundException(id);
    }
    UserResponse userResponse = new UserResponse();
    userResponse.setId(id);
    userResponse.setFname(user.get().getFirstName());
    userResponse.setLname(user.get().getLastName());
    return userResponse;
  }
}
