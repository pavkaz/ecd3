package com.kazh_kvetk.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

  public User(String name, String encryptedPassword) {
    this.name = name;
    this.encryptedPassword = encryptedPassword;
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
