package com.kazh_kvetk.services;

import com.kazh_kvetk.data.*;
import com.kazh_kvetk.data.entities.Student;
import com.kazh_kvetk.data.entities.Teacher;
import com.kazh_kvetk.data.entities.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntitiesInfoProviderImpl implements EntitiesInfoProvider{
  private final TeacherService teacherService;
  private final StudentService studentService;

  @Autowired
  public EntitiesInfoProviderImpl(TeacherService teacherService, StudentService studentService) {
    this.teacherService = teacherService;
    this.studentService = studentService;
  }

  @Override
  public List<Info> take() {
    List<Info> container = new ArrayList<>();
    for (Teacher teacher : teacherService.readAll()) {
      List<Theme> themes = teacher.getThemes();
      if (themes == null) continue;
      for (Theme theme : themes) {
        Student student = studentService.findByTheme(theme);
        if (student == null || student.getMarksList().size() == 0) continue;
        container.add(new Info(teacher, theme, student, student.getMarksList()));
      }
    }
    return container;
  }
}
