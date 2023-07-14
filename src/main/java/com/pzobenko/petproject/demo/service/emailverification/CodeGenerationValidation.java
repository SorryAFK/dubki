package com.pzobenko.petproject.demo.service.emailverification;

import com.pzobenko.petproject.demo.domain.User;
import com.pzobenko.petproject.demo.domain.VerificationCode;
import com.pzobenko.petproject.demo.repository.VerificationCodeRepo;
import com.pzobenko.petproject.demo.repository.UserRepo;
import com.pzobenko.petproject.demo.util.exceptions.CodeDontExistException;
import com.pzobenko.petproject.demo.util.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CodeGenerationValidation {
  private final VerificationCodeRepo codeRepository;
  private final UserRepo userRepo;
  private static final int CODE_LENGTH = 6;
  private static final boolean USE_LETTERS = true;
  private static final boolean USE_NUMBERS = true;

  public String generateNewCode(User userForVerification) {
    log.info("Try to generate verification code for user with username{}",
        userForVerification.getUsername());
    VerificationCode verificationCode = VerificationCode.builder()
        .userId(userForVerification)
        .emailCode(generateCodeUntilItWillUnique())
        .build();
    log.info("Verification code was successfully generated");
    return codeRepository.save(verificationCode).getEmailCode();
  }

  public boolean isCodeValidByCodeAndUser(String code, String  username) {
    VerificationCode verificationCode = codeRepository
        .getVerificationCodeByEmailCode(code)
        .orElseThrow(CodeDontExistException::new);

    User codeOwner = userRepo
        .findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException(username));

    if(verificationCode.getUserId().getId().equals(codeOwner.getId())) {
      codeRepository.delete(verificationCode);
      codeOwner.setEnabled(true);
      userRepo.save(codeOwner);
      return true;
    }

    return false;
  }

  private String generateCodeUntilItWillUnique() {
    String code = randomCode();
    log.info("1");
    while (isCodeUnique(code)) {
      log.info("2");
      code = randomCode();
    }
    log.info("3");
    return code;
  }

  private boolean isCodeUnique(String code) {
    return !codeRepository.getVerificationCodeByEmailCode(code).isEmpty();
  }

  private String randomCode() {
    return RandomStringUtils.
        random(
            CODE_LENGTH,
            USE_LETTERS,
            USE_NUMBERS
        );
  }

}
