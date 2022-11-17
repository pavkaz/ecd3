package com.kazh_kvetk.data.repositories;

import com.kazh_kvetk.data.Student;
import com.kazh_kvetk.data.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
  Student findByTheme(Theme theme);
  boolean existsByTheme(Theme theme);
}
