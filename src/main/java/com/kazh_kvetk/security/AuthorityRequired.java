package com.kazh_kvetk.security;

import java.lang.annotation.*;

@Repeatable(AuthorityRequirements.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AuthorityRequired {
  String value();
}
