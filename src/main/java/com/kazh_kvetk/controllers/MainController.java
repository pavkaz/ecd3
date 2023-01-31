package com.kazh_kvetk.controllers;

import com.kazh_kvetk.data.FacultyGroup;
import com.kazh_kvetk.security.AuthorityRequired;
import com.kazh_kvetk.services.EntitiesInfoProvider;
import com.kazh_kvetk.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Transactional
public class MainController {
  private final EntitiesInfoProvider entitiesInfoProvider;
  private final StudentService studentService;

  public MainController(EntitiesInfoProvider entitiesInfoProvider,
                        StudentService studentService) {
    this.entitiesInfoProvider = entitiesInfoProvider;
    this.studentService = studentService;
  }

  @GetMapping("/info")
  public String infoPage(Model model) {
    model.addAttribute("entities", entitiesInfoProvider.take());
    return "info";
  }

  @GetMapping("/progress")
  public String progressPage(Model model) {
    model.addAttribute(
      "facultyGroups",
      studentService.groupByFaculty()
    );
    return "students-progress";
  }

  @PostMapping("/progress")
  public String progress(Model model, HttpServletRequest request) {
    var year = Integer.parseInt(request.getParameter("year"));
    var semester = Integer.parseInt(request.getParameter("semester"));
    var facultyGroups = studentService.groupByFacultyOnDate(year, semester);

    model.addAttribute("year", year);
    model.addAttribute("semester", semester);
    model.addAttribute("facultyGroups", facultyGroups);

    return "students-progress";
  }
}
