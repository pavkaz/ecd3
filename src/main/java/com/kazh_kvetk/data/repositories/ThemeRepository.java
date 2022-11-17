package com.kazh_kvetk.data.repositories;

import com.kazh_kvetk.data.Theme;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {
  Optional<Theme> findByName(String name);

  @Query(nativeQuery = true, value = "select * from theme left join students s on theme.id = s.theme_id where s.theme_id IS NULL")
  List<Theme> findAllByStudentIsNull();
}
