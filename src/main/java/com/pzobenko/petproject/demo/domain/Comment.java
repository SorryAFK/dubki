package com.pzobenko.petproject.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User author;
  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "post_id", referencedColumnName = "id")
  private Post post;
  @Column(name = "comment")
  private String comment;

  @Override
  public String toString() {
    return "Comment{" +
        "id=" + id +
        ", author=" + author +
        ", post=" + post +
        ", comment='" + comment + '\'' +
        '}';
  }
}
