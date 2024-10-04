package com.example.day1.user;

public class DuplicatedUserFirstnameException extends RuntimeException {

  public DuplicatedUserFirstnameException() {
    super("Firstname is duplicated");
  }
}
