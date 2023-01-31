package com.kazh_kvetk.controllers;

import com.kazh_kvetk.data.entities.Role;
import com.kazh_kvetk.data.entities.User;
import com.kazh_kvetk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Controller
@Transactional
public class UserController {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserController(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/users/login")
  public String loginPage(HttpSession session, Model model) {
    if (session != null ) {
      Exception exception = (Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
      if (exception != null) {
        model.addAttribute("errorMessage", exception.getMessage());
      }
    }
    return "users/login";
  }

  @GetMapping("/users/registration")
  public String registrationPage() {
    return "users/registration";
  }

  @PostMapping("/users/registration")
  public String registration(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
    userService.save(new User(username, passwordEncoder.encode(password), List.of(new Role("ROLE_USER"))));
    return "redirect:/users/login";
  }

  @GetMapping("/users/current/info")
  public String currentInfoPage(Model model, Principal principal) {
    Objects.requireNonNull(principal);
    model.addAttribute("user", userService.read(principal.getName()));
    return "users/info";
  }

  @GetMapping("/users/current/edit")
  public String editCurrentPage(Model model, Principal principal) {
    Objects.requireNonNull(principal);
    User userByUsername = userService.read(principal.getName());
    model.addAttribute("user", userByUsername);
    model.addAttribute("authorise", true);
    return "users/edit";
  }

  @PostMapping("/users/current/edit")
  public String editCurrent(User user, Principal principal) {
    User userByUsername = userService.read(principal.getName());
    userService.update(userByUsername.getId(), user);
    SecurityContextHolder.clearContext();
    return "redirect:/info";
  }

  @PostMapping("/users/current/delete")
  public String deleteCurrent(Principal principal) {
    User src = userService.read(principal.getName());
    userService.delete(src.getId());
    SecurityContextHolder.clearContext();
    return "redirect:/info";
  }
}