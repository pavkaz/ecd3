package com.kazh_kvetk.controllers;

import com.kazh_kvetk.services.EntitiesInfoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@Transactional
public class MainController {
  private final EntitiesInfoProvider entitiesInfoProvider;

  @Autowired
  public MainController(EntitiesInfoProvider entitiesInfoProvider) {
    this.entitiesInfoProvider = entitiesInfoProvider;
  }

  @GetMapping("/info")
  public String infoPage(Model model, Principal principal) {
    model.addAttribute("authorise", principal != null);
    model.addAttribute("entities", entitiesInfoProvider.take());
    return "info";
  }
}
