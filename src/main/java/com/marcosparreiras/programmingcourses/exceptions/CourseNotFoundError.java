package com.marcosparreiras.programmingcourses.exceptions;

public class CourseNotFoundError extends RuntimeException {

  public CourseNotFoundError() {
    super("Course not found");
  }
}
