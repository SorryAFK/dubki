package com.pzobenko.petproject.demo.service.emailverification;

import com.pzobenko.petproject.demo.domain.User;
import com.pzobenko.petproject.demo.service.mailsender.EmailSenderService;
import com.pzobenko.petproject.demo.util.request.EmailVerificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MailVerificationService {
  private final CodeGenerationValidation codeService;
  private final EmailSenderService emailService;
  private static final String CODE_VERIFICATION_SUBJECT = "CODE VERIFICATION";
  private static final String MESSAGE_FOR_USER = "Hey %s! Pls write into our form on our website"
      + " code bellow \n %s";
  private static final String ACCOUNT_VERIFIED = "Account with username %s verified!";
  private static final String ACCOUNT_UNVERIFIED = "Account with username %s UNVERIFIED";

  public String verifyCodeAndUser(String username, EmailVerificationRequest request) {
    if (!codeService.isCodeValidByCodeAndUser(request.getCode(), username))
        return String.format(ACCOUNT_UNVERIFIED, username);
    log.info("User with username {} have successfully verified account", username);
    return String.format(ACCOUNT_VERIFIED, username);
  }

  public void sendVerificationCodeToUser(User user) {
    emailService.sendSimpleMessage(user.getEmail(), CODE_VERIFICATION_SUBJECT,
        String.format(MESSAGE_FOR_USER, user.getUsername(), codeService.generateNewCode(user)));
    log.info("The email with verification code was sent to user with email {}",
        user.getEmail() );
  }
}
