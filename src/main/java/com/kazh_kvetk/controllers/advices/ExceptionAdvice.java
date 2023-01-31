package com.kazh_kvetk.controllers.advices;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler(RuntimeException.class)
  public String handleException(Throwable ex,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("errorMessage", getRootCause(ex).getMessage());
    return "redirect:" + request.getHeader("Referer");
  }

  private Throwable getRootCause(Throwable throwable) {
    while(throwable.getCause() != null) {
      throwable = throwable.getCause();
    }
    return throwable;
  }
}
