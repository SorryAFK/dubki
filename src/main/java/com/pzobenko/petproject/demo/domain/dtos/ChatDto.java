package com.pzobenko.petproject.demo.domain.dtos;

import com.pzobenko.petproject.demo.domain.Message;
import com.pzobenko.petproject.demo.domain.User;
import java.util.List;
import java.util.Set;

public class ChatDto {
  private String chatName;
  private Set<User> chatMembers;
  private List<Message> messages;

}
