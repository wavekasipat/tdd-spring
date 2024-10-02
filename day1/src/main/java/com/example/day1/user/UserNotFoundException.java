package com.example.day1.user;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(int id) {
    super("User id " + id + " not found");
  }
}
