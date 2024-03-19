package com.marcosparreiras.programmingcourses.modules.exceptions;

public class CourseAlreadyExistsError extends Exception {

  public CourseAlreadyExistsError() {
    super("Course already exists error");
  }
}
