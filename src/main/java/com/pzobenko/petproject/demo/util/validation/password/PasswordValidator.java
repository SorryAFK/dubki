package com.pzobenko.petproject.demo.util.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements
    ConstraintValidator<Password, String> {

  @Override
  public void initialize(Password constraint) {
  }

  @Override
  public boolean isValid(String passwordField,
      ConstraintValidatorContext cxt) {
    return passwordField.length() == 8;
  }


}
