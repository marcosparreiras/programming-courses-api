package com.marcosparreiras.programmingcourses.modules.course.controllers;

import com.marcosparreiras.programmingcourses.exceptions.AppRuntimeError;
import com.marcosparreiras.programmingcourses.modules.course.controllers.dtos.ResponseBadRequest;
import com.marcosparreiras.programmingcourses.modules.course.entities.Course;
import com.marcosparreiras.programmingcourses.modules.course.useCases.getCourse.GetCourseUseCase;
import com.marcosparreiras.programmingcourses.modules.course.useCases.getCourse.dtos.GetCourseRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class GetCourseController {

  @Autowired
  private GetCourseUseCase getCourseUseCase;

  private record ResPresenter(Course course) {}

  @GetMapping("/{courseId}")
  public ResponseEntity<Object> show(@PathVariable String courseId) {
    try {
      var getCourseRequestDTO = GetCourseRequestDTO
        .builder()
        .id(courseId)
        .build();
      var getCourseResponseDTO =
        this.getCourseUseCase.execute(getCourseRequestDTO);

      var presenter = new ResPresenter(getCourseResponseDTO.course());
      return ResponseEntity.ok().body(presenter);
    } catch (AppRuntimeError error) {
      return ResponseEntity
        .badRequest()
        .body(new ResponseBadRequest(error.getMessage()));
    }
  }
}
