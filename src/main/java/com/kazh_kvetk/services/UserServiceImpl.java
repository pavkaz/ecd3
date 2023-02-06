package com.kazh_kvetk.services;

import com.kazh_kvetk.data.entities.User;
import com.kazh_kvetk.data.repositories.UserRepository;
import com.kazh_kvetk.data.entities.exceptions.EntityAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository repository;

  @Autowired
  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(User user) {
    String userName = user.getUsername();
    if (repository.findByUsername(userName).isPresent()) {
      throw new EntityAlreadyExistsException(user.getClass(), userName);
    }
    Integer userId = user.getId();
    if (userId != null) {
      throw new EntityAlreadyExistsException(user.getClass(), userId);
    }
    repository.save(user);
  }

  @Override
  public User read(int id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public User read(String username) {
    return repository.findByUsername(username).orElse(null);
  }

  @Override
  public void update(int id, User user) {
    User src = read(id);
    src.update(user);
  }

  @Override
  public void delete(int id) {
    repository.deleteById(id);
  }
}
