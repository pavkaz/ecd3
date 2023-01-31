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
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", unique = true)
  private String name;

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

  public User(String name, String encryptedPassword, Collection<Role> roles) {
    this.name = name;
    this.encryptedPassword = encryptedPassword;
    this.roles = roles;
  }

  public void update(User user) {
    if (user.name != null) {
      this.name = user.name;
    }
    if (user.encryptedPassword != null) {
      this.encryptedPassword = user.encryptedPassword;
    }
  }
}
