package com.pzobenko.petproject.demo.util.exceptions;

public class TokenNotFoundException extends RuntimeException{

  public TokenNotFoundException() {
    super("Token for user not found.");
  }
}
