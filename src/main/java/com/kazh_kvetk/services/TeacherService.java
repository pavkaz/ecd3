package com.kazh_kvetk.services;

import com.kazh_kvetk.data.entities.Teacher;

import java.util.List;

public interface TeacherService {
  void save(Teacher teacher);
  Teacher read(int code);
  List<Teacher> readAll();
  void update(int code, Teacher teacher);
  void delete(int code);
  Teacher findByThemeId(Integer themeId);
}
