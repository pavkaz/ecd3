package com.kazh_kvetk.controllers;

import com.kazh_kvetk.data.FacultyGroup;
import com.kazh_kvetk.data.repositories.StudentRepository;
import com.kazh_kvetk.services.EntitiesInfoProvider;
import com.kazh_kvetk.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@Transactional
public class MainController {
  private final EntitiesInfoProvider entitiesInfoProvider;
  private final StudentService studentService;


  public MainController(EntitiesInfoProvider entitiesInfoProvider,
                        StudentService studentService)
  {
    this.entitiesInfoProvider = entitiesInfoProvider;
    this.studentService = studentService;
  }

  @GetMapping("/info")
  public String infoPage(Model model, Principal principal) {
    model.addAttribute("authorise", principal != null);
    model.addAttribute("entities", entitiesInfoProvider.take());
    return "info";
  }

  @GetMapping("/statement")
  public String statementPage(Model model, Principal principal) {
    model.addAttribute("authorise", principal != null);
    model.addAttribute("facultyGroups", studentService.groupByFaculty());
    return "statement";
  }

  @PostMapping("/statement")
  public String statement(Model model, Principal principal,
                          Integer year, Integer semester) {
    model.addAttribute("authorise", principal != null);
    model.addAttribute("facultyGroups",
      studentService.groupByFacultyOnDate(year, semester));
    model.addAttribute("year", year);
    model.addAttribute("semester", semester);
    return "statement";
  }
}
