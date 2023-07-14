package com.pzobenko.petproject.demo.util.exceptions;


public class UserNotFoundException extends RuntimeException {

  /**
   * The exception is when the user tries to enter the site with a username that is not found.
   */
  public UserNotFoundException(String username) {
    super(String.format("User with username %s not found!", username));
  }

}
