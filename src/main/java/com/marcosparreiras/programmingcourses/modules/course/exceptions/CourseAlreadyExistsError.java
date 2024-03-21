package com.marcosparreiras.programmingcourses.modules.course.exceptions;

import com.marcosparreiras.programmingcourses.exceptions.AppRuntimeError;

public class CourseAlreadyExistsError extends AppRuntimeError {

  public CourseAlreadyExistsError() {
    super("Course already exists error");
  }
}
