package com.marcosparreiras.programmingcourses.modules.course.controllers;

import com.marcosparreiras.programmingcourses.exceptions.AppRuntimeError;
import com.marcosparreiras.programmingcourses.modules.course.controllers.dtos.ResponseBadRequest;
import com.marcosparreiras.programmingcourses.modules.course.useCases.toggleIsActiveCourse.ToggleIsActiveCourseUseCase;
import com.marcosparreiras.programmingcourses.modules.course.useCases.toggleIsActiveCourse.dtos.ToggleIsActiveCourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class ToggleIsActiveCourseController {

  @Autowired
  private ToggleIsActiveCourseUseCase toggleIsActiveCourseUseCase;

  @PatchMapping("/toggle-active/{courseId}")
  public ResponseEntity<Object> toggleIsActive(@PathVariable String courseId) {
    try {
      var toggleIsActiveCourseRequest = ToggleIsActiveCourseRequest
        .builder()
        .id(courseId)
        .build();
      this.toggleIsActiveCourseUseCase.execute(toggleIsActiveCourseRequest);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    } catch (AppRuntimeError error) {
      return ResponseEntity
        .badRequest()
        .body(new ResponseBadRequest(error.getMessage()));
    }
  }
}
