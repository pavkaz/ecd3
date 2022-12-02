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
@Table(name = "marks")
public class Marks implements Comparable<Marks> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "state_exam_grade")
  private int stateExamGrade;

  @Column(name = "thesis_grade")
  private int thesisGrade;

  @Column(name = "year")
  private int year;

  @Column(name = "semester")
  private int semester;

  public Marks(int stateExamGrade, int thesisGrade, int year, int semester) {
    this.stateExamGrade = stateExamGrade;
    this.thesisGrade = thesisGrade;
    this.year = year;
    this.semester = semester;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Marks marks = (Marks) o;
    return stateExamGrade == marks.stateExamGrade && thesisGrade == marks.thesisGrade && year == marks.year && semester == marks.semester && Objects.equals(id, marks.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, stateExamGrade, thesisGrade, year, semester);
  }

  @Override
  public int compareTo(Marks o) {
    int yearsCompare = year - o.year;
    if (yearsCompare == 0) {
      return semester - o.semester;
    } else {
      return yearsCompare;
    }
  }
}
