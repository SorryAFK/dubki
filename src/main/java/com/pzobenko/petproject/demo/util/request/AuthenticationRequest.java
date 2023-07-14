package com.pzobenko.petproject.demo.util.request;

import com.pzobenko.petproject.demo.util.validation.password.Password;
import com.pzobenko.petproject.demo.util.validation.username.Username;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

  @Username
  private String username;
  @Password
  private String password;
}
