package com.kazh_kvetk.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.CascadeType.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Integer id;

  @Column(name = "username", unique = true)
  private String username;

  @Column(name = "encrypted_password", length = 1024)
  private String encryptedPassword;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {PERSIST, MERGE})
  @JoinTable(
    name = "users_roles",
    joinColumns = @JoinColumn(
       name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(
      name = "role_id", referencedColumnName = "id"))
  private Collection<Role> roles;

  public User(String username, String encryptedPassword, Collection<Role> roles) {
    this.username = username;
    this.encryptedPassword = encryptedPassword;
    this.roles = roles;
  }

  public void update(User user) {
    if (user.username != null) {
      this.username = user.username;
    }
    if (user.encryptedPassword != null) {
      this.encryptedPassword = user.encryptedPassword;
    }
  }
}
