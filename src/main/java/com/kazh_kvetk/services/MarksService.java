package com.kazh_kvetk.services;

import com.kazh_kvetk.data.Marks;
import com.kazh_kvetk.exceptions.EntityAlreadyExistsException;

public interface MarksService {
  void save(Marks marks);
  Marks read(int id);
  void update(int id, Marks marks);
  void delete(int id);
}
