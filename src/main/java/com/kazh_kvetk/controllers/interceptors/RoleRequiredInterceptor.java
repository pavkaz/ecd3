package com.kazh_kvetk.controllers.interceptors;

import com.kazh_kvetk.security.AuthorityRequired;
import com.kazh_kvetk.security.authorities.exceptions.IllegalAuthoritiesException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class RoleRequiredInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    if (handler instanceof HandlerMethod) {
      var handlerAnnotations = ((HandlerMethod) handler).getMethod().getDeclaredAnnotations();
      var foundAnnotation = Arrays.stream(handlerAnnotations)
        .filter(annotation -> annotation.annotationType().equals(AuthorityRequired.class))
        .map(annotation -> (AuthorityRequired) annotation)
        .findAny();

      if (foundAnnotation.isPresent()) {
        var userAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        var isEqualsAuthorities = userAuthorities.stream()
          .anyMatch(authority -> authority.getAuthority().equals(foundAnnotation.get().value()));

        if (!isEqualsAuthorities) {
          throw new IllegalAuthoritiesException("Insufficient rights: "
            + userAuthorities + " to perform the action");
        }
      }
    }
    return true;
  }
}
