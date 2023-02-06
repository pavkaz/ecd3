package com.kazh_kvetk.services.factories.registration;

import com.kazh_kvetk.services.factories.BaseUserFactory;
import com.kazh_kvetk.services.factories.UserFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationPagesFactory {
  private final List<String> supportedUserTypes;
  private final RegistrationPageNamesConventionStrategy namesConventionStrategy;

  public RegistrationPagesFactory(UserFactory userFactory,
                                  RegistrationPageNamesConventionStrategy namesConventionStrategy) {
    supportedUserTypes = userFactory.getSupportedTypes();
    this.namesConventionStrategy = namesConventionStrategy;
  }

  public String recognize(String type) {
    if (!supportedUserTypes.contains(type)) {
      throw new IllegalArgumentException("Registration page for type: " + type + " not found");
    }
    return namesConventionStrategy.apply(type);
  }
}

