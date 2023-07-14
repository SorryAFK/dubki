package com.pzobenko.petproject.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "author_id", referencedColumnName = "id")
  private User authorId;
  @Column(name = "description", nullable = false)
  private String description;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "likes",
      joinColumns = @JoinColumn(name = "post_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<User> likes = new LinkedHashSet<>();
  @Column(name = "likes_count", nullable = false)
  private Long likesCount;
  @Column(name = "time", nullable = false)
  private LocalDateTime timestampWhenWasPostCreated;
}
