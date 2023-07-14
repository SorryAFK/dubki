package com.pzobenko.petproject.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pzobenko.petproject.demo.domain.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "username", unique = true, nullable = false)
  private String username;
  @Column(name = "email", unique = true, nullable = false)
  private String email;
  @JsonIgnore
  @Column(name = "password", nullable = false)
  private String password;
  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Role role;
  @Column(name = "is_enabled")
  private boolean enabled;
  @Column(name = "is_account_non_expired")
  private boolean accountNonExpired;
  @Column(name = "is_account_non_locked")
  private boolean accountNonLocked;
  @Column(name = "is_credentials_non_expired")
  private boolean credentialsNonExpired;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }
  @Override
  public String getPassword() {
    return this.password;
  }
  @Override
  public String getUsername() {
    return this.username;
  }
  @Override
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }
  @Override
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }
  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }
  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    User user = (User) o;

    if (!id.equals(user.id)) {
      return false;
    }
    if (!username.equals(user.username)) {
      return false;
    }
    if (!email.equals(user.email)) {
      return false;
    }
    if (!password.equals(user.password)) {
      return false;
    }
    return role == user.role;
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + username.hashCode();
    result = 31 * result + email.hashCode();
    result = 31 * result + password.hashCode();
    result = 31 * result + role.hashCode();
    return result;
  }
}
