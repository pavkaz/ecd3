package com.kazh_kvetk.security.authorities;

import com.kazh_kvetk.data.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class DummyAuthoritiesProvider implements AuthoritiesProvider {
  @Override
  public Collection<GrantedAuthority> apply(User user) {
    return new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_USER")));
  }
}
