package com.kazh_kvetk.services;

import com.kazh_kvetk.data.entities.Theme;

import java.util.List;

public interface ThemeService {
  void save(Theme theme);
  Theme read(int id);
  Theme read(String name);
  List<Theme> readAll();
  List<Theme> readAllWhereStudentNull();
  void update(int id, Theme theme);
  void delete(int id);
}
