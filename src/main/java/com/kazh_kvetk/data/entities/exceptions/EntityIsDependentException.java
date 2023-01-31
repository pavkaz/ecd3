package com.kazh_kvetk.data.entities.exceptions;

public class EntityIsDependentException extends RuntimeException{

  public EntityIsDependentException(Class<?> parentClass, int parentId,
                                    Class<?> dependentClass, int dependentId) {
    super("Parent entity " + parentClass.getName() +
      " with id: " + parentId +
      " dependent with entity " + dependentClass.getName() +
      " with id: " + dependentId);
  }
}
