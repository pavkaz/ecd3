package com.kazh_kvetk.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer recordBookNumber;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "faculty")
  private String faculty;

  @Column(name = "group_name")
  private String groupName;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  private Theme theme;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Marks marks;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return Objects.equals(fullName, student.fullName) && Objects.equals(faculty, student.faculty) && Objects.equals(groupName, student.groupName) && Objects.equals(theme, student.theme) && Objects.equals(marks, student.marks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fullName, faculty, groupName, theme, marks);
  }

  @Override
  public String toString() {
    return "Student{" +
      "recordBookNumber=" + recordBookNumber +
      ", fullName='" + fullName + '\'' +
      ", faculty='" + faculty + '\'' +
      ", groupName='" + groupName + '\'' +
      ", marks=" + marks +
      '}';
  }
}
