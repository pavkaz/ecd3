package com.kazh_kvetk.controllers.interceptors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthoriseAddingInterceptor implements HandlerInterceptor {

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response,
                         Object handler, ModelAndView modelAndView) {
    if (modelAndView != null) {
      var authentication = SecurityContextHolder.getContext().getAuthentication();
      var authorise = authentication.getPrincipal() != null;
      modelAndView.getModelMap().addAttribute("authorise", authorise);
    }
  }
}
