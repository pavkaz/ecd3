package com.kazh_kvetk.services;

import com.kazh_kvetk.data.Teacher;
import com.kazh_kvetk.data.Theme;
import com.kazh_kvetk.exceptions.EntityAlreadyExistsException;
import com.kazh_kvetk.exceptions.EntityIsDependentException;
import org.hibernate.persister.entity.SingleTableEntityPersister;

import java.util.List;

public interface TeacherService {
  void save(Teacher teacher);
  Teacher read(int code);
  List<Teacher> readAll();
  void update(int code, Teacher teacher);
  void delete(int code);
  Teacher findByThemeId(Integer themeId);
}
