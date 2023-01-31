package com.kazh_kvetk.services;

import com.kazh_kvetk.data.FacultyGroup;
import com.kazh_kvetk.data.entities.Student;
import com.kazh_kvetk.data.entities.Theme;

import java.util.List;

public interface StudentService {
  void save(Student student);
  Student read(int recordBookNumber);
  List<Student> readAll();
  void update(int recordBookNumber, Student student);
  void delete(int recordBookNumber);
  Student findByTheme(Theme theme);
  boolean existsByTheme(Theme theme);
  List<FacultyGroup> groupByFaculty();
  List<FacultyGroup> groupByFacultyOnDate(int year, int semester);
}
