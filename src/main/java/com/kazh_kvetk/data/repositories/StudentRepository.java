package com.kazh_kvetk.data.repositories;

import com.kazh_kvetk.data.FacultyGroup;
import com.kazh_kvetk.data.entities.Student;
import com.kazh_kvetk.data.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
  Student findByTheme(Theme theme);

  boolean existsByTheme(Theme theme);

  @Query(nativeQuery = true, value = "select " +
    "g.faculty as faculty," +
    "g.avg_grade_by_faculty as avgGradeByFaculty, " +
    "g.group_name as groupName, " +
    "g.avg_grade_by_group as avgGradeByGroup, " +
    "s.full_name as studentFullName, " +
    "m.state_exam_grade as stateExamGrade, " +
    "m.thesis_grade as thesisGrade, " +
    "((m.state_exam_grade + m.thesis_grade) / 2) as avgGrade" +
    " from students s" +
    "         join marks m" +
    "              on m.student_record_book_number = s.record_book_number" +
    "         join (select fg.faculty," +
    "                      fg.avg_grade_by_faculty," +
    "                      s.group_name," +
    "                      (avg(m.state_exam_grade) + avg(m.thesis_grade)) / 2 as avg_grade_by_group" +
    "               from students s" +
    "                        join marks m" +
    "                             on m.student_record_book_number = s.record_book_number" +
    "                        join (select s.faculty," +
    "                                     (avg(m.state_exam_grade) + avg(m.thesis_grade)) / 2 as avg_grade_by_faculty" +
    "                              from students s" +
    "                                       join marks m on m.student_record_book_number = s.record_book_number" +
    "                              group by s.faculty) fg" +
    "                             on fg.faculty = s.faculty" +
    "               group by s.faculty, s.group_name) g" +
    "              on ((g.faculty = s.faculty) and (g.group_name = s.group_name))")
  List<FacultyGroup> groupByFaculty();

  @Query(nativeQuery = true, value = "select " +
    "g.faculty as faculty," +
    "g.avg_grade_by_faculty as avgGradeByFaculty, " +
    "g.group_name as groupName, " +
    "g.avg_grade_by_group as avgGradeByGroup, " +
    "s.full_name as studentFullName, " +
    "m.state_exam_grade as stateExamGrade, " +
    "m.thesis_grade as thesisGrade, " +
    "((m.state_exam_grade + m.thesis_grade) / 2) as avgGrade" +
    " from students s" +
    "         join marks m" +
    "              on m.student_record_book_number = s.record_book_number" +
    "         join (select fg.faculty," +
    "                      fg.avg_grade_by_faculty," +
    "                      s.group_name," +
    "                      (avg(m.state_exam_grade) + avg(m.thesis_grade)) / 2 as avg_grade_by_group" +
    "               from students s" +
    "                        join marks m" +
    "                             on m.student_record_book_number = s.record_book_number" +
    "                        join (select s.faculty," +
    "                                     (avg(m.state_exam_grade) + avg(m.thesis_grade)) / 2 as avg_grade_by_faculty" +
    "                              from students s" +
    "                                       join marks m on m.student_record_book_number = s.record_book_number" +
    "                                       where m.year = :year and m.semester = :semester" +
    "                              group by s.faculty) fg" +
    "                             on fg.faculty = s.faculty" +
    "               where m.year = :year and m.semester = :semester " +
    "               group by s.faculty, s.group_name) g" +
    "              on ((g.faculty = s.faculty) and (g.group_name = s.group_name))" +
    "              where m.year = :year and m.semester = :semester")
  List<FacultyGroup> groupByFacultyInDate(@Param("year") int year, @Param("semester") int semester);
}
