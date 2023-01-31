package com.kazh_kvetk.security.startup;

import com.kazh_kvetk.data.entities.Role;
import com.kazh_kvetk.data.entities.User;
import com.kazh_kvetk.data.repositories.RoleRepository;
import com.kazh_kvetk.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Component
@PropertySource("classpath:admin_setup.properties")
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
  private boolean alreadySetup = false;

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  private final Environment env;

  @Autowired
  public SetupDataLoader(UserRepository userRepository,
                         RoleRepository roleRepository,
                         PasswordEncoder passwordEncoder,
                         Environment env) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.env = env;
  }

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (alreadySetup) return;
    setupAdmin();
    alreadySetup = true;
  }

  private void setupAdmin() {
    var userName = env.getProperty("admin.name");
    var userPassword = env.getProperty("admin.password");
    var roleNames = env.getProperty("admin.roles", String[].class);

    var roles = createRolesIfNotExistsAndGet(Objects.requireNonNull(roleNames));
    var user = createUserIfNotExistsAndGet(userName, passwordEncoder.encode(userPassword), roles);
    userRepository.save(user);
  }

  private Collection<Role> createRolesIfNotExistsAndGet(String... names) {
    return new ArrayList<>() {
      {
        for (String name : names) {
          var role = roleRepository.findByName(name);
          add(role == null ? new Role(name) : role);
        }
      }
    };
  }

  private User createUserIfNotExistsAndGet(String name, String passHash, Collection<Role> roles) {
    return userRepository.findByName(name)
      .orElseGet(() -> new User(name, passHash, roles));
  }
}
