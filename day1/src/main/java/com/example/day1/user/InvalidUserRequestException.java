package com.example.day1.user;

public class InvalidUserRequestException extends RuntimeException {

  public InvalidUserRequestException() {
    super("Invalid user request");
  }
}
