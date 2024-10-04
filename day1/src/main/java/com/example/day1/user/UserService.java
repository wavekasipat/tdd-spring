package com.example.day1.user;

import jakarta.transaction.Transactional;
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
    userResponse.setAge(user.get().getAge());
    return userResponse;
  }

  @Transactional
  public UserResponse create(UserRequest userRequest) {
    // check firstname duplicate
    if (userRepository.existsByFirstName(userRequest.getFname())) {
      throw new DuplicatedUserFirstnameException();
    }

    MyUser user = new MyUser();
    user.setFirstName(userRequest.getFname());
    user.setLastName(userRequest.getLname());
    user.setAge(userRequest.getAge());
    user = userRepository.saveAndFlush(user);
    System.out.println(user);

    UserResponse userResponse = new UserResponse();
    userResponse.setId(Math.toIntExact(user.getId()));
    userResponse.setFname(user.getFirstName());
    userResponse.setLname(user.getLastName());
    userResponse.setAge(user.getAge());
    System.out.println(userResponse);

    return userResponse;
  }
}
