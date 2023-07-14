package com.pzobenko.petproject.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_data")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserData {
  @Id
  @Column(name = "user_id")
  private Long userId;
  @OneToOne
  @MapsId
  private User user;
  @Column(name = "firstname")
  private String firstName;
  @Column(name = "lastname")
  private String lastName;
  @Column(name = "avatar")
  private String avatar;
  @Column(name = "dob")
  private LocalDate dob;
  @Column(name = "subscribes_count", nullable = false)
  private Long subscribesCount;
  @Column(name = "followers_count", nullable = false)
  private Long followersCount;
  @Column(name = "time_of_creating", nullable = false)
  private LocalDateTime timestampWhenWasAccCreated;

}

