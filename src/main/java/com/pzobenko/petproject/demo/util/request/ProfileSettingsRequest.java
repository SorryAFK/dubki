package com.pzobenko.petproject.demo.util.request;

import com.pzobenko.petproject.demo.util.validation.name.Name;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
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
public class ProfileSettingsRequest {
  @Name
  private String firstName;
  @Name
  private String lastName;
  @NotEmpty
  private String avatarUrl;
  @Past
  private LocalDate dateOfBirthday;

}
