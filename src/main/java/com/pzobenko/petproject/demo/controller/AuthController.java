package com.pzobenko.petproject.demo.controller;

import com.pzobenko.petproject.demo.service.CurrentUserService;
import com.pzobenko.petproject.demo.service.auth.AuthService;
import com.pzobenko.petproject.demo.service.emailverification.MailVerificationService;
import com.pzobenko.petproject.demo.util.exceptions.UserAlreadyExistException;
import com.pzobenko.petproject.demo.util.exceptions.UsernameNotFoundException;
import com.pzobenko.petproject.demo.util.request.AuthenticationRequest;
import com.pzobenko.petproject.demo.util.request.EmailVerificationRequest;
import com.pzobenko.petproject.demo.util.request.RefreshRequest;
import com.pzobenko.petproject.demo.util.request.RegisterRequest;
import com.pzobenko.petproject.demo.util.response.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthController {

  private final AuthService authenticationService;
  private final MailVerificationService verificationService;

  /**
   * The method registers a new user in the application.
   *
   * @param request data entered by the user for registration.
   * @return response about the status of user registration.
   */
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) throws UserAlreadyExistException {
    log.info("User with username {} trying to register.", request.getUsername());
    return ResponseEntity.ok(authenticationService.register(request));
  }

  /**
   * The method performs user authentication in the application.
   *
   * @param request data entered by the user for authentication.
   * @return response about the status of user authentication.
   */
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody AuthenticationRequest request) throws UsernameNotFoundException {
    log.info("User with username {} trying to log in.", request.getUsername());
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }

  @PostMapping("/verify")
  public ResponseEntity<String> verify(
      @RequestBody EmailVerificationRequest request) {
    return ResponseEntity.ok(verificationService
        .verifyCodeAndUser(CurrentUserService.getCurrentUsername(),request));
  }

  @PostMapping("/refresh")
  public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody RefreshRequest request) {
    log.info("Some of users trying to refresh tokens by refresh token.");
    return ResponseEntity.ok(authenticationService.refreshAllTokens(request));
  }
}
