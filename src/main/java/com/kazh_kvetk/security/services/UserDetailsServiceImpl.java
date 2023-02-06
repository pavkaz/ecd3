package com.kazh_kvetk.security.services;

import com.kazh_kvetk.data.entities.User;
import com.kazh_kvetk.security.authorities.AuthoritiesProvider;
import com.kazh_kvetk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserService service;
  private final AuthoritiesProvider provider;

  @Autowired
  public UserDetailsServiceImpl(UserService service, @Qualifier("defaultAuthoritiesProvider") AuthoritiesProvider provider) {
    this.service = service;
    this.provider = provider;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    User src = service.read(username);
    if (src == null) {
      throw new UsernameNotFoundException("User with name: " + username + "not found");
    }
    return new org.springframework.security.core.userdetails.User(
      src.getUsername(), src.getEncryptedPassword(), provider.apply(src)
    );
  }
}
