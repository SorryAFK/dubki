package com.pzobenko.petproject.demo.util.request;

import com.pzobenko.petproject.demo.domain.User;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRequest {
  private String name;
  private Set<User> chatMembers;
}
