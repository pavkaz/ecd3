package com.kazh_kvetk.services;

import com.kazh_kvetk.data.entities.Theme;
import com.kazh_kvetk.data.repositories.ThemeRepository;
import com.kazh_kvetk.data.entities.exceptions.EntityAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ThemeServiceImpl implements ThemeService {
  private final ThemeRepository repository;

  @Autowired
  public ThemeServiceImpl(ThemeRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(Theme theme) {
    Integer id = theme.getId();
    if (id != null) {
      Theme src = read(id);
      if (src == null) {
        repository.save(theme);
      } else {
        throw new EntityAlreadyExistsException(src.getClass(), id);
      }
    } else {
      repository.save(theme);
    }
  }

  @Override
  public Theme read(int id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public Theme read(String name) {
    return repository.findByName(name).orElse(null);
  }

  @Override
  public List<Theme> readAll() {
    return repository.findAll();
  }

  @Override
  public List<Theme> readAllWhereStudentNull() {
    return repository.findAllByStudentIsNull();
  }

  @Override
  public void update(int id, Theme theme) {
    Theme src = read(id);
    if (src != null) {
      theme.setId(src.getId());
      repository.save(theme);
    }
  }

  @Override
  public void delete(int id) {
    repository.deleteById(id);
  }
}
