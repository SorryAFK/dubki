package com.pzobenko.petproject.demo.util.validation.username;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements
    ConstraintValidator<Username, String> {

  @Override
  public void initialize(Username constraint) {
  }

  @Override
  public boolean isValid(String nameField,
      ConstraintValidatorContext cxt) {
    return nameField.length() < 30 && nameField.length() > 2;
  }
}
