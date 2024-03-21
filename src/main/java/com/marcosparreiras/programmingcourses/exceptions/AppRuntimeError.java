package com.marcosparreiras.programmingcourses.exceptions;

public class AppRuntimeError extends RuntimeException {

  public AppRuntimeError(String message) {
    super(message);
  }
}
