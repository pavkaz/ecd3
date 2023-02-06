package com.kazh_kvetk.services.factories;

import com.kazh_kvetk.data.entities.Student;
import com.kazh_kvetk.data.entities.Teacher;
import com.kazh_kvetk.data.entities.User;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class BaseUserFactory implements UserFactory {
  private final List<Class<? extends User>> types = List.of(Student.class, Teacher.class);

  public User build(String type, Map<String, ?> params) {
    Class<? extends User> findClass = types.stream()
      .filter(t -> t.getSimpleName().toLowerCase(Locale.ROOT).equals(type))
      .findAny()
      .orElseThrow(() -> new IllegalArgumentException("Class type with name: " + type + " is not found"));

    User user;
    try {
      user = findClass.getDeclaredConstructor().newInstance();
      for(Field field : getAllDeclaredFields(findClass)) {
        field.setAccessible(true);
        field.set(user, params.get(field.getName()));
      }
    } catch (NoSuchMethodException | InstantiationException
             | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
    return user;
  }

  private List<Field> getAllDeclaredFields(Class<?> type) {
    var declaredFields = new ArrayList<>(Arrays.stream(type.getDeclaredFields()).toList());
    while((type = type.getSuperclass()) != null) {
      declaredFields.addAll(Arrays.stream(type.getDeclaredFields()).toList());
    }
    return declaredFields;
  }

  public List<String> getSupportedTypes() {
    return types.stream()
      .map(type -> type.getSimpleName().toLowerCase(Locale.ROOT))
      .toList();
  }

}
