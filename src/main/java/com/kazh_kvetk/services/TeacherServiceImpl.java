package com.kazh_kvetk.services;

import com.kazh_kvetk.data.entities.Student;
import com.kazh_kvetk.data.entities.Teacher;
import com.kazh_kvetk.data.entities.Theme;
import com.kazh_kvetk.data.repositories.TeacherRepository;
import com.kazh_kvetk.data.entities.exceptions.EntityAlreadyExistsException;
import com.kazh_kvetk.data.entities.exceptions.EntityIsDependentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
  private final TeacherRepository repository;
  private final StudentService studentService;
  @Autowired
  public TeacherServiceImpl(TeacherRepository repository, StudentService studentService) {
    this.repository = repository;
    this.studentService = studentService;
  }

  @Override
  public void save(Teacher teacher) {
    Integer code = teacher.getId();
    if (code != null) {
      Teacher src = read(code);
      if (src == null) {
        repository.save(teacher);
      } else {
        throw new EntityAlreadyExistsException(teacher.getClass(), code);
      }
    } else {
      repository.save(teacher);
    }
  }

  @Override
  public Teacher read(int code) {
    return repository.findById(code).orElse(null);
  }

  @Override
  public List<Teacher> readAll() {
    return repository.findAll();
  }

  @Override
  public void update(int code, Teacher teacher) {
    Teacher src = read(code);
    if (src != null) {
      teacher.setId(src.getId());
      repository.save(teacher);
    }
  }

  @Override
  public void delete(int code) {
    Teacher teacher = read(code);
    for (Theme theme : teacher.getThemes()) {
      Student student = studentService.findByTheme(theme);
      if (student != null) {
        throw new EntityIsDependentException(
          teacher.getClass(), teacher.getId(),
          student.getClass(), student.getId());
      }
    }
    repository.deleteById(code);
  }

  @Override
  public Teacher findByThemeId(Integer themeId) {
    return repository.findByThemeId(themeId);
  }
}
