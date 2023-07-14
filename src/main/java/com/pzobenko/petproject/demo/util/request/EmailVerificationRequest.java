package com.pzobenko.petproject.demo.util.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class EmailVerificationRequest {

  @NotEmpty
  @Size(min = 6, max = 6)
  private String code;

}
