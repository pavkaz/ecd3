package com.kazh_kvetk.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.TreeSet;

@Data
@Getter
@Setter
public class InfoEntity {
  private final Teacher teacher;
  private final Theme theme;
  private final Student student;
  private final TreeSet<Marks> marks;

  public InfoEntity(Teacher teacher, Theme theme, Student student, List<Marks> marks) {
    this.teacher = teacher;
    this.theme = theme;
    this.student = student;
    this.marks = new TreeSet<>(marks);
  }
}