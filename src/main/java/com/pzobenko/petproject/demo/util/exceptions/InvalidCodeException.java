package com.pzobenko.petproject.demo.util.exceptions;

public class InvalidCodeException extends RuntimeException {

  public InvalidCodeException() {
    super("The code have invalid or wrong value");
  }
}
