package com.kazh_kvetk.controllers;

import com.kazh_kvetk.data.entities.Student;
import com.kazh_kvetk.security.AuthorityRequired;
import com.kazh_kvetk.services.StudentService;
import com.kazh_kvetk.services.TeacherService;
import com.kazh_kvetk.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Transactional
public class StudentController {
  private final StudentService studentService;
  private final TeacherService teacherService;
  private final ThemeService themeService;

  @Autowired
  public StudentController(StudentService studentService,
                           TeacherService teacherService,
                           ThemeService themeService
  ) {
    this.studentService = studentService;
    this.teacherService = teacherService;
    this.themeService = themeService;
  }

  @GetMapping("/students")
  public String allPage(Model model) {
    model.addAttribute("students", studentService.readAll());
    return "students";
  }

  @GetMapping("/students/{recordBookNumber}")
  public String infoPage(Model model,
                         @PathVariable("recordBookNumber") Integer recordBookNumber) {
    Student student = studentService.read(recordBookNumber);
    model.addAttribute("teacher", teacherService.findByThemeId(student.getTheme().getId()));
    model.addAttribute("student", student);
    return "student";
  }

  @GetMapping("/students/add")
  @AuthorityRequired("ROLE_ADMIN")
  public String addStudentPage(Model model) {
    model.addAttribute("themes", themeService.readAllWhereStudentNull());
    return "student-add";
  }

  @PostMapping("/students/add")
  @AuthorityRequired("ROLE_ADMIN")
  public String addStudent(Student student, String themeName) {
    student.setTheme(themeService.read(themeName));
    studentService.save(student);
    return "redirect:/info";
  }

  @PostMapping("/students/{recordBookNumber}/delete")
  @AuthorityRequired("ROLE_ADMIN")
  public String deleteStudent(@PathVariable("recordBookNumber") Integer recordBookNumber) {
    studentService.delete(recordBookNumber);
    return "redirect:/info";
  }
}
