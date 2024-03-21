package com.marcosparreiras.programmingcourses.modules.course.controllers;

import com.marcosparreiras.programmingcourses.exceptions.CourseNotFoundError;
import com.marcosparreiras.programmingcourses.exceptions.dtos.ErrorMessageDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.DeleteCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.ToggleCourseIsActiveRequest;
import com.marcosparreiras.programmingcourses.modules.course.useCases.DeleteCourseUseCase;
import com.marcosparreiras.programmingcourses.modules.course.useCases.ToggleCourseIsActiveUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

  @Autowired
  private DeleteCourseUseCase deleteCourseUseCase;

  @Autowired
  private ToggleCourseIsActiveUseCase toggleCourseIsActiveUseCase;

  @DeleteMapping("/{courseId}")
  public ResponseEntity<Object> delete(@PathVariable String courseId) {
    try {
      var deleteCourseRequestDTO = DeleteCourseRequestDTO
        .builder()
        .id(courseId)
        .build();
      this.deleteCourseUseCase.execute(deleteCourseRequestDTO);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    } catch (CourseNotFoundError error) {
      return ResponseEntity
        .badRequest()
        .body(new ErrorMessageDTO(error.getMessage(), null));
    }
  }

  @PatchMapping("/toggle-active/{courseId}")
  public ResponseEntity<Object> toggleIsActive(@PathVariable String courseId) {
    try {
      var toggleCourseIsActiveRequest = ToggleCourseIsActiveRequest
        .builder()
        .id(courseId)
        .build();
      this.toggleCourseIsActiveUseCase.execute(toggleCourseIsActiveRequest);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    } catch (CourseNotFoundError error) {
      return ResponseEntity
        .badRequest()
        .body(new ErrorMessageDTO(error.getMessage(), null));
    }
  }
}
