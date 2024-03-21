package com.marcosparreiras.programmingcourses.modules.course.controllers;

import com.marcosparreiras.programmingcourses.exceptions.dtos.ErrorMessageDTO;
import com.marcosparreiras.programmingcourses.modules.course.useCases.updateCourse.UpdateCourseUseCase;
import com.marcosparreiras.programmingcourses.modules.course.useCases.updateCourse.dtos.UpdateCourseRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class UpdateCourseController {

  @Autowired
  private UpdateCourseUseCase updateCourseUseCase;

  private record ReqBody(String name, String category) {}

  @PutMapping("/{courseId}")
  public ResponseEntity<Object> update(
    @PathVariable String courseId,
    @RequestBody ReqBody body
  ) {
    try {
      var updateCourseRequestDTO = UpdateCourseRequestDTO
        .builder()
        .id(courseId)
        .name(body.name())
        .category(body.category())
        .build();

      this.updateCourseUseCase.execute(updateCourseRequestDTO);

      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    } catch (Exception error) {
      return ResponseEntity
        .badRequest()
        .body(new ErrorMessageDTO(error.getMessage(), null));
    }
  }
}
