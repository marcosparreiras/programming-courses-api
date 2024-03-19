package com.marcosparreiras.programmingcourses.exceptions;

public class CourseAlreadyExistsError extends Exception {

  public CourseAlreadyExistsError() {
    super("Course already exists error");
  }
}
