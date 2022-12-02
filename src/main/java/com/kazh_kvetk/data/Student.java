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

  @JoinColumn(name = "student_record_book_number")
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Marks> marksList;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return Objects.equals(recordBookNumber, student.recordBookNumber) && Objects.equals(fullName, student.fullName) && Objects.equals(faculty, student.faculty) && Objects.equals(groupName, student.groupName) && Objects.equals(theme, student.theme) && Objects.equals(marksList, student.marksList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recordBookNumber, fullName, faculty, groupName, theme, marksList);
  }

  @Override
  public String toString() {
    return "Student{" +
      "recordBookNumber=" + recordBookNumber +
      ", fullName='" + fullName + '\'' +
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
