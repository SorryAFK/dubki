package com.pzobenko.petproject.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "username", unique = true, nullable = false)
  private String username;
  @Column(name = "email", unique = true, nullable = false)
  private String email;
  @Column(name = "firstname", nullable = false)
  private String firstname;
  @Column(name = "lastname", nullable = false)
  private String lastname;
  @JsonIgnore
  @Column(name = "password")
  private String password;
  @Column(name = "subscribes_count", nullable = false)
  private Long subscribesCount;
  @Column(name = "followers_count", nullable = false)
  private Long followersCount;
  @ManyToMany(targetEntity = )
  @JoinColumn
  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", subscribesCount=" + subscribesCount +
        ", followersCount=" + followersCount +
        '}';
  }
}
