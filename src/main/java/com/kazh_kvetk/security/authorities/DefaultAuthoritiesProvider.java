package com.kazh_kvetk.security.authorities;

import com.kazh_kvetk.data.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DefaultAuthoritiesProvider implements AuthoritiesProvider {
  @Override
  public Collection<GrantedAuthority> apply(User user) {
    var roles = user.getRoles();
    return new ArrayList<>(roles.stream()
      .map(role -> new SimpleGrantedAuthority(role.getName()))
      .toList());

  }
}
