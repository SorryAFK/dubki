package com.pzobenko.petproject.demo.util.validation.name;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
@Log4j2
class NamesValidatorTest {

  @Test
  void isValid() {
    String str = "abc123";
    for (var ch : str.toCharArray()) {
      System.out.print(ch + " ");
    }
  }
}