package com.kazh_kvetk.services;

import com.kazh_kvetk.data.InfoEntity;
import com.kazh_kvetk.data.Student;
import com.kazh_kvetk.data.Teacher;
import com.kazh_kvetk.data.Theme;
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
  public List<InfoEntity> take() {
    List<InfoEntity> container = new ArrayList<>();
    for (Teacher teacher : teacherService.readAll()) {
      List<Theme> themes = teacher.getThemes();
      if (themes == null) continue;
      for (Theme theme : themes) {
        Student student = studentService.findByTheme(theme);
        if (student == null) continue;
        container.add(new InfoEntity(teacher, theme, student, student.getMarks()));
      }
    }
    return container;
  }
}
