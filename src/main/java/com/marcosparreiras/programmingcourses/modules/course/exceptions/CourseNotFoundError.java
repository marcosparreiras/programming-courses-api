package com.marcosparreiras.programmingcourses.modules.course.exceptions;

import com.marcosparreiras.programmingcourses.exceptions.AppRuntimeError;

public class CourseNotFoundError extends AppRuntimeError {

  public CourseNotFoundError() {
    super("Course not found");
  }
}
