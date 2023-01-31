package com.kazh_kvetk.services;

import com.kazh_kvetk.data.entities.Marks;

public interface MarksService {
  void save(Marks marks);
  Marks read(int id);
  void update(int id, Marks marks);
  void delete(int id);
}
