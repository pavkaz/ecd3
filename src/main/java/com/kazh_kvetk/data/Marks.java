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
public class Marks {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "state_exam_grade")
  private int stateExamGrade;

  @Column(name = "thesis_grade")
  private int thesisGrade;

  public Marks(int stateExamGrade, int thesisGrade) {
    this.stateExamGrade = stateExamGrade;
    this.thesisGrade = thesisGrade;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Marks marks = (Marks) o;
    return stateExamGrade == marks.stateExamGrade && thesisGrade == marks.thesisGrade;
  }

  @Override
  public int hashCode() {
    return Objects.hash(stateExamGrade, thesisGrade);
  }

  @Override
  public String toString() {
    return "Marks{" +
      "id=" + id +
      ", stateExamGrade=" + stateExamGrade +
      ", thesisGrade=" + thesisGrade +
      '}';
  }
}
