package com.kazh_kvetk.security.authorities;

import com.kazh_kvetk.data.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface AuthoritiesProvider {
  Collection<GrantedAuthority> apply(User user);
}
