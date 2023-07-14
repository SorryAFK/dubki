package com.pzobenko.petproject.demo.util.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  private String jwtToken;
  private String refreshToken;
}
