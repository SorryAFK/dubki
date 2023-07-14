//package com.pzobenko.petproject.demo.domain;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.MapsId;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//import java.util.ArrayList;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Set;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Builder
//@Table(name = "chats")
//public class Chat {
//  @Id
//  @Column(name = "id")
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long id;
//  @Column(name = "name", nullable = false)
//  private String chatName;
////  @OneToOne
////  @MapsId
////  @JoinColumn(name = "admin", table = "users", referencedColumnName = "id")
////  private User admin;
//  @ManyToMany(fetch = FetchType.EAGER)
//  @JoinTable(name = "chat_members",
//      joinColumns = @JoinColumn(name = "chat_id"),
//      inverseJoinColumns = @JoinColumn(name = "user_id"))
//  private Set<User> chatMembers = new LinkedHashSet<>();
//  @OneToMany(fetch = FetchType.EAGER)
//  @JoinTable(name = "chat_members",
//      joinColumns = @JoinColumn(name = "chat_id"),
//      inverseJoinColumns = @JoinColumn(name = "user_id"))
//  private List<Message> messageList = new ArrayList<>();
//
//
//}
