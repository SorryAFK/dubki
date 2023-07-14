package com.pzobenko.petproject.demo.util.exceptions;

public class RoleNotExistException extends RuntimeException {

  public RoleNotExistException(String userRole) {
    super(String.format("Role with name %s not found.", userRole));
  }
}
