package com.kazh_kvetk.services;

import com.kazh_kvetk.data.entities.Marks;
import com.kazh_kvetk.data.repositories.MarksRepository;
import com.kazh_kvetk.data.entities.exceptions.EntityAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MarksServiceImpl implements MarksService {
  private final MarksRepository repository;

  @Autowired
  public MarksServiceImpl(MarksRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(Marks marks) {
    Integer id = marks.getId();
    if (id != null) {
      Marks src = read(id);
      if (src == null) {
        repository.save(marks);
      } else {
        throw new EntityAlreadyExistsException(this.getClass(), id);
      }
    } else {
      repository.save(marks);
    }
  }

  @Override
  public Marks read(int id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public void update(int id, Marks marks) {
    Marks src = read(id);
    if (src != null) {
      marks.setId(src.getId());
      repository.save(marks);
    }
  }

  @Override
  public void delete(int id) {
    repository.deleteById(id);
  }
}
