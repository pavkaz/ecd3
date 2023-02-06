package com.kazh_kvetk.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher extends User {
  private String fullName;

  private String degree;

  private String academicRank;

  private String department;

  private String phoneNumber;

  @Column(unique = true)
  private String email;

  @JoinColumn(name = "teacher_code")
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Theme> themes;

  public Teacher(String username, String encryptedPassword, Collection<Role> roles,
                 String fullName, String degree, String academicRank, String department,
                 String phoneNumber, String email, List<Theme> themes) {
    super(username, encryptedPassword, roles);
    this.fullName = fullName;
    this.degree = degree;
    this.academicRank = academicRank;
    this.department = department;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.themes = themes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Teacher teacher = (Teacher) o;
    return Objects.equals(fullName, teacher.fullName) && Objects.equals(degree, teacher.degree) && Objects.equals(academicRank, teacher.academicRank) && Objects.equals(department, teacher.department) && Objects.equals(phoneNumber, teacher.phoneNumber) && Objects.equals(email, teacher.email) && Objects.equals(themes, teacher.themes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), fullName, degree, academicRank, department, phoneNumber, email, themes);
  }

  @Override
  public String toString() {
    return "Teacher{" +
      "fullName='" + fullName + '\'' +
      ", degree='" + degree + '\'' +
      ", academicRank='" + academicRank + '\'' +
      ", department='" + department + '\'' +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", email='" + email + '\'' +
      ", themes=" + themes +
      '}';
  }

  public void addTheme(Theme theme) {
    themes.add(theme);
  }
}
