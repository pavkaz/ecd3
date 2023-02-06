package com.kazh_kvetk.data.repositories;

import com.kazh_kvetk.data.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends UserBaseRepository<Teacher> {
  @Query(nativeQuery = true,
    value = "select * from teachers t join theme th on t.code = th.teacher_code where th.id = ?1")
  Teacher findByThemeId(Integer themeId);
}
