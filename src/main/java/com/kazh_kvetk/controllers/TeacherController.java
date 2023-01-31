package com.kazh_kvetk.controllers;

import com.kazh_kvetk.data.entities.Teacher;
import com.kazh_kvetk.data.entities.Theme;
import com.kazh_kvetk.security.AuthorityRequired;
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
public class TeacherController {
  private final TeacherService teacherService;
  private final ThemeService themeService;

  @Autowired
  public TeacherController(TeacherService teacherService, ThemeService themeService) {
    this.teacherService = teacherService;
    this.themeService = themeService;
  }

  @GetMapping("/teachers")
  public String infoPage(Model model) {
    model.addAttribute("teachers", teacherService.readAll());
    return "teachers";
  }

  @GetMapping("/teachers/{code}")
  public String oneTeacherInfoPage(Model model, @PathVariable("code") Integer code) {
    model.addAttribute("teacher", teacherService.read(code));
    return "teacher";
  }

  @PostMapping("/teachers/{code}/delete")
  @AuthorityRequired("ROLE_ADMIN")
  public String deleteTeacher(@PathVariable("code") Integer code) {
    teacherService.delete(code);
    return "redirect:/info";
  }

  @GetMapping("/teachers/themes/add")
  @AuthorityRequired("ROLE_ADMIN")
  public String selectTeacherToThemePage(Model model) {
    model.addAttribute("teachers", teacherService.readAll());
    return "teacher-select";
  }

  @GetMapping("/teachers/add")
  @AuthorityRequired("ROLE_ADMIN")
  public String addTeacherPage() {
    return "teacher-add";
  }

  @PostMapping("/teachers/add")
  @AuthorityRequired("ROLE_ADMIN")
  public String addTeacher(Teacher teacher) {
    teacherService.save(teacher);
    return "redirect:/info";
  }

  @GetMapping("/teachers/themes")
  public String allPage(Model model) {
    model.addAttribute("teachers", teacherService.readAll());
    return "themes";
  }

  @GetMapping("/teachers/{code}/themes/{id}")
  public String themeInfoPage(Model model,
                              @PathVariable("code") Integer code, @PathVariable("id") Integer id) {
    model.addAttribute("theme", themeService.read(id));
    model.addAttribute("teacher", teacherService.read(code));
    return "theme";
  }

  @GetMapping("/teachers/{code}/themes/add")
  @AuthorityRequired("ROLE_ADMIN")
  public String addThemePage(@PathVariable("code") Integer code, Model model) {
    Teacher teacher = teacherService.read(code);
    if (teacher == null) {
      throw new IllegalArgumentException("Teacher with code: " + code + " not found");
    }
    model.addAttribute("code", code);
    return "theme-add";
  }

  @PostMapping("/teachers/{code}/themes/add")
  @AuthorityRequired("ROLE_ADMIN")
  public String addTheme(@PathVariable("code") Integer code, Theme theme) {
    Teacher teacher = teacherService.read(code);
    if (teacher != null) {
      teacher.addTheme(theme);
    }
    return "redirect:/info";
  }

  @PostMapping("/teachers/{code}/themes/{id}/delete")
  @AuthorityRequired("ROLE_ADMIN")
  public String deleteTheme(@PathVariable("code") Integer code,
                            @PathVariable("id") Integer id) {
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
