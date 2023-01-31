package com.kazh_kvetk.security.authorities.exceptions;

public class IllegalAuthoritiesException extends RuntimeException {
  public IllegalAuthoritiesException() {
    super();
  }

  public IllegalAuthoritiesException(String message) {
    super(message);
  }
}
