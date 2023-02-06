package com.kazh_kvetk.data.repositories;

import com.kazh_kvetk.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends JpaRepository<T, Integer> {
  Optional<T> findByUsername(String name);
}
