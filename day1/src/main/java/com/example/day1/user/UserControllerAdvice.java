package com.example.day1.user;

import com.example.day1.global.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFoundException(
    UserNotFoundException ex
  ) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getMessage());
    return new ResponseEntity<ErrorResponse>(
      errorResponse,
      HttpStatus.NOT_FOUND
    );
  }

  // handle DuplicatedUserFirstnameException
  @ExceptionHandler(DuplicatedUserFirstnameException.class)
  public ResponseEntity<ErrorResponse> handleDuplicatedUserFirstnameException(
    DuplicatedUserFirstnameException ex
  ) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getMessage());
    return new ResponseEntity<ErrorResponse>(
      errorResponse,
      HttpStatus.BAD_REQUEST
    );
  }
}
