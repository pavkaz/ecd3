package com.kazh_kvetk.services.factories;

import com.kazh_kvetk.data.entities.User;

import java.util.List;
import java.util.Map;

public interface UserFactory {
  User build(String type, Map<String, ?> params);
  List<String> getSupportedTypes();
}
