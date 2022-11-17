package com.kazh_kvetk.controllers;

import com.kazh_kvetk.data.Marks;
import com.kazh_kvetk.data.Student;
import com.kazh_kvetk.exceptions.EntityAlreadyExistsException;
import com.kazh_kvetk.services.MarksService;
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

import java.security.Principal;

@Controller
@Transactional
public class StudentController {
  private final StudentService studentService;
  private final TeacherService teacherService;
  private final ThemeService themeService;
  private final MarksService marksService;

  @Autowired
  public StudentController(StudentService studentService,
                           TeacherService teacherService,
                           ThemeService themeService,
                           MarksService marksService) {
    this.studentService = studentService;
    this.teacherService = teacherService;
    this.themeService = themeService;
    this.marksService = marksService;
  }

  @GetMapping("/students")
  public String allPage(Model model, Principal principal) {
    model.addAttribute("authorise", principal != null);
    model.addAttribute("students", studentService.readAll());
    return "students";
  }

  @GetMapping("/students/{recordBookNumber}")
  public String infoPage(Model model, Principal principal,
                         @PathVariable("recordBookNumber") Integer recordBookNumber) {
    model.addAttribute("authorise", principal != null);
    Student student = studentService.read(recordBookNumber);
    model.addAttribute("teacher", teacherService.findByThemeId(student.getTheme().getId()));
    model.addAttribute("student", student);
    return "student";
  }

  @GetMapping("/students/add")
  public String addStudentPage(Model model, Principal principal) {
    model.addAttribute("authorise", principal != null);
    model.addAttribute("themes", themeService.readAllWhereStudentNull());
    return "student-add";
  }

  @PostMapping("/students/add")
  public String addStudent(Student student, String themeName,
                           Integer stateExamGrade, Integer thesisGrade, Model model) {
    Marks marks = new Marks(stateExamGrade, thesisGrade);
    marksService.save(marks);

    student.setMarks(marks);
    student.setTheme(themeService.read(themeName));
    studentService.save(student);
    return "redirect:/info";
  }

  @PostMapping("/students/{recordBookNumber}/delete")
  public String deleteStudent(@PathVariable("recordBookNumber") Integer recordBookNumber) {
    studentService.delete(recordBookNumber);
    return "redirect:/info";
  }
}
