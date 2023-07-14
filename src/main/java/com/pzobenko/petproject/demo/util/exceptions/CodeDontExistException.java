package com.pzobenko.petproject.demo.util.exceptions;

public class CodeDontExistException extends RuntimeException{

  public CodeDontExistException() {
    super("Code that you sent doesn't exist!");
  }
}
