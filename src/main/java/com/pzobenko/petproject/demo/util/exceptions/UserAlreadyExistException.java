package com.pzobenko.petproject.demo.util.exceptions;

public class UserAlreadyExistException extends Exception{

  public UserAlreadyExistException(String username) {

    super(String.format("User with username %s already exist.",username));
  }
}
