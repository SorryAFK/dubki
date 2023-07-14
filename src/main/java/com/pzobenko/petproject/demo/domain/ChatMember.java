//package com.pzobenko.petproject.demo.domain;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.MapsId;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Builder
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "chat_members")
//public class ChatMember {
//  @Id
//  @Column(name = "user_id")
//  private Long userId;
//  @OneToOne
//  @MapsId
//  @JoinColumn(table = "users", referencedColumnName = "id", name = "user_id")
//  private User user;
//
//  @OneToOne
//  @MapsId
//  @JoinColumn(table = "chats", referencedColumnName = "id", name = "chat_id")
//  private Chat chat;
//
//  @Column(name = "is_approved", nullable = false)
//  private boolean isMemberApproved;
//  @Column(name = "is_admin", nullable = false)
//  private boolean isMemberAdmin;
//
//}
