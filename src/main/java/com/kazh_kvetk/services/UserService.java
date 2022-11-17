package com.kazh_kvetk.services;


import com.kazh_kvetk.data.User;
import com.kazh_kvetk.exceptions.EntityAlreadyExistsException;

public interface UserService {
  void save(User user);
  User read(int id);
  User read(String username);
  void update(int id, User user);
  void delete(int id);
}
