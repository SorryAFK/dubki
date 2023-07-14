package com.pzobenko.petproject.demo.util.validation.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements
    ConstraintValidator<Email, String> {
  private static final String DOT_COM = ".com";
  @Override
  public void initialize(Email constraint) {
  }

  @Override
  public boolean isValid(String emailField,
      ConstraintValidatorContext cxt) {
    return emailField.chars().filter(x -> ((char) x == '@')).count() == 1 &&
         emailField.contains(DOT_COM);
  }
}
