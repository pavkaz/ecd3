package com.kazh_kvetk.services;

import com.kazh_kvetk.data.Student;
import com.kazh_kvetk.data.Theme;
import com.kazh_kvetk.data.repositories.StudentRepository;
import com.kazh_kvetk.exceptions.EntityAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
  private final StudentRepository repository;

  @Autowired
  public StudentServiceImpl(StudentRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(Student student) {
    Integer recordBookNumber = student.getRecordBookNumber();
    if (recordBookNumber != null) {
      Student src = read(recordBookNumber);
      if (src == null) {
        repository.save(student);
      } else {
        throw new EntityAlreadyExistsException(src.getClass(), recordBookNumber);
      }
    } else {
      repository.save(student);
    }
  }

  @Override
  public Student read(int recordBookNumber) {
    return repository.findById(recordBookNumber).orElse(null);
  }

  @Override
  public List<Student> readAll() {
    return repository.findAll();
  }

  @Override
  public void update(int recordBookNumber, Student student) {
    Student src = read(recordBookNumber);
    if (src != null) {
      student.setRecordBookNumber(src.getRecordBookNumber());
      repository.save(student);
    }
  }

  @Override
  public void delete(int recordBookNumber) {
    repository.deleteById(recordBookNumber);
  }

  @Override
  public Student findByTheme(Theme theme) {
    return repository.findByTheme(theme);
  }

  @Override
  public boolean existsByTheme(Theme theme) {
    return repository.existsByTheme(theme);
  }
}
