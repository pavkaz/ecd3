package com.kazh_kvetk.services;

import com.kazh_kvetk.data.Student;
import com.kazh_kvetk.data.Theme;
import com.kazh_kvetk.exceptions.EntityAlreadyExistsException;

import java.util.List;

public interface StudentService {
  void save(Student student);
  Student read(int recordBookNumber);
  List<Student> readAll();
  void update(int recordBookNumber, Student student);
  void delete(int recordBookNumber);
  Student findByTheme(Theme theme);
  boolean existsByTheme(Theme theme);
}
