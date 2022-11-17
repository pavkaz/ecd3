package com.kazh_kvetk.services;

import com.kazh_kvetk.data.User;
import com.kazh_kvetk.security.authorities.AuthoritiesProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
  public UserDetailsServiceImpl(UserService service, AuthoritiesProvider provider) {
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
      src.getName(), src.getEncryptedPassword(), provider.apply(src)
    );
  }
}
