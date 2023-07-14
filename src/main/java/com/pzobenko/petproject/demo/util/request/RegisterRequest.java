package com.pzobenko.petproject.demo.util.request;

import com.pzobenko.petproject.demo.util.validation.email.Email;
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
public class RegisterRequest {

//  @Username
  private String username;
//  @Email
  private String email;
//  @Password
  private String password;

}
