package com.pzobenko.petproject.demo.service.auth;

import com.pzobenko.petproject.demo.domain.RefreshToken;
import com.pzobenko.petproject.demo.domain.User;
import com.pzobenko.petproject.demo.domain.enums.Role;
import com.pzobenko.petproject.demo.repository.RefreshTokenRepo;
import com.pzobenko.petproject.demo.repository.UserRepo;
import com.pzobenko.petproject.demo.service.emailverification.MailVerificationService;
import com.pzobenko.petproject.demo.util.exceptions.TokenNotFoundException;
import com.pzobenko.petproject.demo.util.exceptions.UserAlreadyExistException;
import com.pzobenko.petproject.demo.util.exceptions.UsernameNotFoundException;
import com.pzobenko.petproject.demo.util.request.AuthenticationRequest;
import com.pzobenko.petproject.demo.util.request.RefreshRequest;
import com.pzobenko.petproject.demo.util.request.RegisterRequest;
import com.pzobenko.petproject.demo.util.response.AuthenticationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service which manage registration and authentication.
 */
@Service
@AllArgsConstructor
@Log4j2
public class AuthService {

  private final UserRepo userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final RefreshTokenRepo tokenRepo;
  private final MailVerificationService mailService;

  /**
   * Register method which take data from request and create new user. After all this steps saving
   * this user to db.
   *
   * @param request request with info about user.
   * @return JWT token.
   */
  public AuthenticationResponse register(RegisterRequest request) throws UserAlreadyExistException {

    String username = request.getUsername();

    if (userRepository.existsByUsername(username)) {
      throw new UserAlreadyExistException(username);
    }

    User user = buildNewUser(request);

    userRepository.save(user);

    // Log info about user who had registered in db.
    log.info("User register with username {} successfully.", username);

    mailService.sendVerificationCodeToUser(user);

    return buildNewTokens(user);
  }

  /**
   * Authentication method which giving jwt token for user what have registered before.
   *
   * @param request request with username and password.
   * @return JWT token.
   */
  public AuthenticationResponse authenticate(AuthenticationRequest request)
      throws UsernameNotFoundException {

    String username = request.getUsername();

    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            username,
            request.getPassword()
        )
    );

    log.info("User have log in successfully.");

    return updateExistsTokens(user,
        tokenRepo.findRefreshTokenByOwnerOfToken(user)
            .orElseThrow(TokenNotFoundException::new)
    );
  }


  /**
   * Create new user entity
   *
   * @param request contains info about new user
   * @return user entity
   */
  private User buildNewUser(RegisterRequest request) {
    return User.builder()
        .username(request.getUsername())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .enabled(false)
        .accountNonExpired(true)
        .accountNonLocked(true)
        .credentialsNonExpired(true)
        .build();
  }

  /**
   * Build new tokens for user
   *
   * @param user to create new tokens
   * @return two tokens
   */
  private AuthenticationResponse buildNewTokens(User user) {
    RefreshToken refreshTokenForDb = new RefreshToken();
    refreshTokenForDb.setOwnerOfToken(user);

    var jwtToken = jwtService.generateToken(user);

    var refreshToken = jwtService.generateRefreshToken(user, refreshTokenForDb.getId());

    refreshTokenForDb.setTokenForRefresh(refreshToken);

    tokenRepo.save(refreshTokenForDb);

    return AuthenticationResponse.builder()
        .jwtToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  /**
   * Recreating and saving new tokens into db
   *
   * @param user     to update exists tokens
   * @param oldToken old refresh token to verify request
   * @return new two tokens
   */
  public AuthenticationResponse updateExistsTokens(User user, RefreshToken oldToken) {
    var jwtToken = jwtService.generateToken(user);

    var refreshToken = jwtService.generateRefreshToken(user, oldToken.getId());
    oldToken.setTokenForRefresh(refreshToken);

    tokenRepo.save(oldToken);

    return AuthenticationResponse.builder()
        .jwtToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  /**
   * Refresh all tokens.
   *
   * @param request contains refresh token
   * @return two new tokens
   */
  public AuthenticationResponse refreshAllTokens(RefreshRequest request) {
    RefreshToken oldRefreshToken = tokenRepo
        .findRefreshTokenByTokenForRefresh(request.getRefreshToken())
        .orElseThrow(TokenNotFoundException::new);

    return updateExistsTokens(oldRefreshToken.getOwnerOfToken(), oldRefreshToken);
  }
}
