package com.marcosparreiras.programmingcourses.modules.course.controllers;

import com.marcosparreiras.programmingcourses.exceptions.AppRuntimeError;
import com.marcosparreiras.programmingcourses.modules.course.controllers.dtos.ResponseBadRequest;
import com.marcosparreiras.programmingcourses.modules.course.useCases.deleteCourse.DeleteCourseUseCase;
import com.marcosparreiras.programmingcourses.modules.course.useCases.deleteCourse.dtos.DeleteCourseRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class DeleteCourseController {

  @Autowired
  private DeleteCourseUseCase deleteCourseUseCase;

  @DeleteMapping("/{courseId}")
  public ResponseEntity<Object> delete(@PathVariable String courseId) {
    try {
      var deleteCourseRequestDTO = DeleteCourseRequestDTO
        .builder()
        .id(courseId)
        .build();
      this.deleteCourseUseCase.execute(deleteCourseRequestDTO);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    } catch (AppRuntimeError error) {
      return ResponseEntity
        .badRequest()
        .body(new ResponseBadRequest(error.getMessage()));
    }
  }
}
