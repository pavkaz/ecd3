package com.kazh_kvetk.data.entities.exceptions;

public class EntityAlreadyExistsException extends RuntimeException{
  public EntityAlreadyExistsException() {
    super();
  }

  public EntityAlreadyExistsException(Class<?> entityClass, int id) {
    super("Entity " + entityClass.getName() + " with id: " + id + " already exists");
  }

  public EntityAlreadyExistsException(Class<?> entityClass, String name) {
    super("Entity " + entityClass.getName() + " with name: " + name + " already exists");
  }
}
