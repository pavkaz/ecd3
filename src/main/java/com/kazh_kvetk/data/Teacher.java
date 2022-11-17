package com.kazh_kvetk.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer code;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "degree")
  private String degree;

  @Column(name = "title")
  private String title;

  @Column(name = "department")
  private String department;

  @Column(name = "phoneNumber")
  private String phoneNumber;

  @Column(name = "email", unique = true)
  private String email;

  @JoinColumn(name = "teacher_code")
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Theme> themes;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Teacher teacher = (Teacher) o;
    return Objects.equals(fullName, teacher.fullName) && Objects.equals(degree, teacher.degree) && Objects.equals(title, teacher.title) && Objects.equals(department, teacher.department) && Objects.equals(phoneNumber, teacher.phoneNumber) && Objects.equals(email, teacher.email) && Objects.equals(themes, teacher.themes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fullName, degree, title, department, phoneNumber, email, themes);
  }

  public void addTheme(Theme theme) {
    themes.add(theme);
  }
}
