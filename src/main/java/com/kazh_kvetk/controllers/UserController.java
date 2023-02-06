package com.kazh_kvetk.controllers;

import com.kazh_kvetk.data.entities.Role;
import com.kazh_kvetk.data.entities.User;
import com.kazh_kvetk.services.UserService;
import com.kazh_kvetk.services.factories.UserFactory;
import com.kazh_kvetk.services.factories.registration.RegistrationPagesFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Transactional
public class UserController {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final UserFactory userFactory;
  private final RegistrationPagesFactory registrationPagesFactory;

  @Autowired
  public UserController(UserService userService, PasswordEncoder passwordEncoder,
                        UserFactory userFactory, RegistrationPagesFactory registrationPagesFactory) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.userFactory = userFactory;
    this.registrationPagesFactory = registrationPagesFactory;
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
  public String registrationPage(Model model) {
    var supportedTypes = userFactory.getSupportedTypes();
    model.addAttribute("supportedTypes", supportedTypes);
    return "users/registration";
  }

  @PostMapping("/users/registration/1")
  public String registrationFirstStage(HttpSession session,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("userType") String userType) {
    session.setAttribute("username", username);
    session.setAttribute("encryptedPassword", passwordEncoder.encode(password));
    session.setAttribute("userType", userType);
    return "users/" + registrationPagesFactory.recognize(userType);
  }

  @PostMapping("/users/registration/2")
  public String registrationSecondStage(HttpSession session, HttpServletRequest request) {
    var parameterMap = request.getParameterMap().entrySet().stream()
      .filter(e -> !e.getKey().equals("_csrf"))
      .collect(Collectors.toMap(Map.Entry::getKey, e -> (Object) e.getValue()[0]));

    var username = session.getAttribute("username");
    var encryptedPassword = session.getAttribute("encryptedPassword");
    var userType = (String) session.getAttribute("userType");
    parameterMap.put("username", username);
    parameterMap.put("encryptedPassword", encryptedPassword);
    parameterMap.put("roles", List.of(new Role("ROLE_USER")));

    var user = userFactory.build(userType, parameterMap);
    userService.save(user);
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