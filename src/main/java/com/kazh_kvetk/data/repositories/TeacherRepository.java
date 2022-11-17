package com.kazh_kvetk.data.repositories;

import com.kazh_kvetk.data.Student;
import com.kazh_kvetk.data.Teacher;
import com.kazh_kvetk.data.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
  @Query(nativeQuery = true,
    value = "select * from teachers t join theme th on t.code = th.teacher_code where th.id = ?1")
  Teacher findByThemeId(Integer themeId);
}
