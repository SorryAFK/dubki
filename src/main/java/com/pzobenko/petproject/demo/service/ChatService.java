//package com.pzobenko.petproject.demo.service;
//
//import com.pzobenko.petproject.demo.domain.Chat;
//import com.pzobenko.petproject.demo.repository.ChatRepository;
//import com.pzobenko.petproject.demo.util.request.CreateChatRequest;
//import java.util.ArrayList;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public class ChatService {
//  private final ChatRepository chatRepo;
//
//  public Chat createNewChat(CreateChatRequest request) {
//    Chat newChat = Chat.builder()
//        .chatName(request.getName())
//        .chatMembers(request.getChatMembers())
//        .messageList(new ArrayList<>())
//        .build();
//
//      return null;
//  }
//}
