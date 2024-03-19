package com.marcosparreiras.programmingcourses.modules.course.controllers;

import com.marcosparreiras.programmingcourses.exceptions.CourseAlreadyExistsError;
import com.marcosparreiras.programmingcourses.exceptions.dtos.ErrorMessageDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.CreateCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.CreateCourseResponseDTO;
import com.marcosparreiras.programmingcourses.modules.course.useCases.CreateCourseUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

  @Autowired
  private CreateCourseUseCase createCourseUseCase;

  @PostMapping("")
  public ResponseEntity<Object> create(
    @RequestBody CreateCourseRequestDTO createCourseRequestDTO
  ) {
    try {
      var course = this.createCourseUseCase.execute(createCourseRequestDTO);
      return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new CreateCourseResponseDTO(course.getId().toString()));
    } catch (CourseAlreadyExistsError error) {
      return ResponseEntity
        .badRequest()
        .body(new ErrorMessageDTO(error.getMessage(), "name"));
    }
  }
}
