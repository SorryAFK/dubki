package com.pzobenko.petproject.demo.util.validation.name;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements
    ConstraintValidator<Name, String> {

  @Override
  public void initialize(Name constraint) {
  }

  @Override
  public boolean isValid(String nameField,
      ConstraintValidatorContext cxt) {
    return nameField.chars().allMatch(x -> Character.isLetter((char) x)) &&
        nameField.length() < 30 && nameField.length() > 2;
  }
}
