package com.kazh_kvetk.controllers;

import com.kazh_kvetk.data.entities.Marks;
import com.kazh_kvetk.data.entities.Student;
import com.kazh_kvetk.security.AuthorityRequired;
import com.kazh_kvetk.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Transactional
public class MarksController {
  private final StudentService studentService;

  @Autowired
  public MarksController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/students/{recordBookNumber}/marks")
  public String marksPage(@PathVariable("recordBookNumber") Integer recordBookNumber,
                          Model model) {
    Student student = studentService.read(recordBookNumber);
    model.addAttribute("student", student);
    model.addAttribute("allMarks", student.getMarksList());
    return "all-marks";
  }

  @GetMapping("/students/marks/add")
  @AuthorityRequired("ROLE_ADMIN")
  public String addMarksPage(Model model) {
    model.addAttribute("students", studentService.readAll());
    return "student-select";
  }

  @GetMapping("/students/{recordBookNumber}/marks/add")
  @AuthorityRequired("ROLE_ADMIN")
  public String addMarksForStudentPage(Model model,
                                       @PathVariable("recordBookNumber") Integer recordBookNumber) {
    Student student = studentService.read(recordBookNumber);
    if (student == null) {
      model.addAttribute("errorMessage", "There are no students");
    } else {
      model.addAttribute("student", student);
    }
    return "marks-add";
  }

  @PostMapping("/students/{recordBookNumber}/marks/add")
  @AuthorityRequired("ROLE_ADMIN")
  public String addMarksForStudent(Marks marks,
                                   @PathVariable("recordBookNumber") Integer recordBookNumber) {
    Student student = studentService.read(recordBookNumber);
    student.addMarks(marks);
    return "redirect:/info";
  }
}
