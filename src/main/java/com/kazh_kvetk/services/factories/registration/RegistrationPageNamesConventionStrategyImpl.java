package com.kazh_kvetk.services.factories.registration;

import org.springframework.stereotype.Component;

@Component
public class RegistrationPageNamesConventionStrategyImpl implements RegistrationPageNamesConventionStrategy {
  @Override
  public String apply(String type) {
    return "registration-" + type;
  }
}
