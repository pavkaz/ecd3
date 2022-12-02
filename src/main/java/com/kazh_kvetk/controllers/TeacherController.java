package com.kazh_kvetk.controllers;

import com.kazh_kvetk.data.Teacher;
import com.kazh_kvetk.data.Theme;
import com.kazh_kvetk.exceptions.EntityAlreadyExistsException;
import com.kazh_kvetk.exceptions.EntityIsDependentException;
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
public class TeacherController {
  private final TeacherService teacherService;
  private final ThemeService themeService;

  @Autowired
  public TeacherController(TeacherService teacherService, ThemeService themeService) {
    this.teacherService = teacherService;
    this.themeService = themeService;
  }

  @GetMapping("/teachers")
  public String infoPage(Model model, Principal principal) {
    model.addAttribute("authorise", principal != null);
    model.addAttribute("teachers", teacherService.readAll());
    return "teachers";
  }

  @GetMapping("/teachers/{code}")
  public String oneTeacherInfoPage(Model model, Principal principal, @PathVariable("code") Integer code) {
    model.addAttribute("authorise", principal != null);
    model.addAttribute("teacher", teacherService.read(code));
    return "teacher";
  }

  @PostMapping("/teachers/{code}/delete")
  public String deleteTeacher(@PathVariable("code") Integer code) {
    teacherService.delete(code);
    return "redirect:/info";
  }

  @GetMapping("/teachers/themes/add")
  public String selectTeacherToThemePage(Model model, Principal principal) {
    model.addAttribute("authorise", principal != null);
    model.addAttribute("teachers", teacherService.readAll());
    return "teacher-select";
  }

  @GetMapping("/teachers/add")
  public String addTeacherPage(Model model, Principal principal) {
    model.addAttribute("authorise", principal != null);
    return "teacher-add";
  }

  @PostMapping("/teachers/add")
  public String addTeacher(Teacher teacher, Model model) {
    teacherService.save(teacher);
    return "redirect:/info";
  }

  @GetMapping("/teachers/themes")
  public String allPage(Model model, Principal principal) {
    model.addAttribute("authorise", principal != null);
    model.addAttribute("teachers", teacherService.readAll());
    return "themes";
  }

  @GetMapping("/teachers/{code}/themes/{id}")
  public String themeInfoPage(Model model, Principal principal,
                              @PathVariable("code") Integer code, @PathVariable("id") Integer id) {
    model.addAttribute("authorise", principal != null);
    model.addAttribute("theme", themeService.read(id));
    model.addAttribute("teacher", teacherService.read(code));
    return "theme";
  }

  @GetMapping("/teachers/{code}/themes/add")
  public String addThemePage(@PathVariable("code") Integer code, Model model, Principal principal) {
    model.addAttribute("authorise", principal != null);

    Teacher teacher = teacherService.read(code);
    if (teacher == null) {
      throw new IllegalArgumentException("Teacher with code: " + code + " not found");
    }
    model.addAttribute("code", code);
    return "theme-add";
  }

  @PostMapping("/teachers/{code}/themes/add")
  public String addTheme(@PathVariable("code") Integer code, Theme theme) {
    Teacher teacher = teacherService.read(code);
    if (teacher != null) {
      teacher.addTheme(theme);
    }
    return "redirect:/info";
  }

  @PostMapping("/teachers/{code}/themes/{id}/delete")
  public String deleteTheme(@PathVariable("code") Integer code, @PathVariable("id") Integer id, Model model) {
    Teacher teacher = teacherService.read(code);
    if (teacher != null) {
      Theme theme = themeService.read(id);
      if (theme != null) {
        themeService.delete(id);
      }
    }
    return "redirect:/info";
  }
}
