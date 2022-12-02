package com.kazh_kvetk.controllers;

import com.kazh_kvetk.data.Marks;
import com.kazh_kvetk.data.Student;
import com.kazh_kvetk.services.MarksService;
import com.kazh_kvetk.services.StudentService;
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
public class MarksController {
  private final StudentService studentService;
  private final MarksService marksService;

  @Autowired
  public MarksController(StudentService studentService, MarksService marksService) {
    this.studentService = studentService;
    this.marksService = marksService;
  }

  @GetMapping("/students/{recordBookNumber}/marks")
  public String marksPage(@PathVariable("recordBookNumber") Integer recordBookNumber,
                          Model model, Principal principal)
  {
    Student student = studentService.read(recordBookNumber);
    model.addAttribute("authorise", principal != null);
    model.addAttribute("student", student);
    model.addAttribute("allMarks", student.getMarksList());
    return "all-marks";
  }

  @GetMapping("/students/marks/add")
  public String addMarksPage(Model model, Principal principal) {
    model.addAttribute("authorise", principal != null);
    model.addAttribute("students", studentService.readAll());
    return "student-select";
  }

  @GetMapping("/students/{recordBookNumber}/marks/add")
  public String addMarksForStudentPage(Model model, Principal principal,
                             @PathVariable("recordBookNumber") Integer recordBookNumber)
  {
    Student student = studentService.read(recordBookNumber);
    if (student == null) {
      model.addAttribute("errorMessage", "There are no students");
    } else {
      model.addAttribute("student", student);
    }
    model.addAttribute("authorise", principal != null);
    return "marks-add";
  }

  @PostMapping("/students/{recordBookNumber}/marks/add")
  public String addMarksForStudent(Marks marks,
                         @PathVariable("recordBookNumber") Integer recordBookNumber)
  {
    Student student = studentService.read(recordBookNumber);
    student.addMarks(marks);
    return "redirect:/info";
  }
}
