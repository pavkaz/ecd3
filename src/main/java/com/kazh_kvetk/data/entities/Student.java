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
@Table(name = "students")
public class Student extends User {

  @Column(name = "full_name")
  private String fullName;

  private String faculty;

  @Column(name = "group_name")
  private String groupName;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  private Theme theme;

  @JoinColumn(name = "student_record_book_number")
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Marks> marksList;

  public Student(String name, String encryptedPassword, Collection<Role> roles,
                 String fullName, String faculty, String groupName, Theme theme,
                 List<Marks> marksList) {
    super(name, encryptedPassword, roles);
    this.fullName = fullName;
    this.faculty = faculty;
    this.groupName = groupName;
    this.theme = theme;
    this.marksList = marksList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Student student = (Student) o;
    return Objects.equals(fullName, student.fullName) && Objects.equals(faculty, student.faculty) && Objects.equals(groupName, student.groupName) && Objects.equals(theme, student.theme) && Objects.equals(marksList, student.marksList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), fullName, faculty, groupName, theme, marksList);
  }

  @Override
  public String toString() {
    return "Student{" +
      "fullName='" + fullName + '\'' +
      ", faculty='" + faculty + '\'' +
      ", groupName='" + groupName + '\'' +
      ", theme=" + theme +
      ", marksList=" + marksList +
      '}';
  }

  public void addMarks(Marks marks) {
    marksList.add(marks);
  }
}
