package com.marcosparreiras.programmingcourses.modules.course.controllers;

import com.marcosparreiras.programmingcourses.exceptions.CourseAlreadyExistsError;
import com.marcosparreiras.programmingcourses.exceptions.dtos.ErrorMessageDTO;
import com.marcosparreiras.programmingcourses.modules.course.useCases.createCourse.CreateCourseUseCase;
import com.marcosparreiras.programmingcourses.modules.course.useCases.createCourse.dtos.CreateCourseRequestDTO;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CreateCourseController {

  @Autowired
  private CreateCourseUseCase createCourseUseCase;

  private record ReqBody(
    @NonNull
    @Length(min = 3, message = "Name field should have at least 3 characters")
    String name,
    @NonNull
    @Length(
      min = 3,
      message = "category field should have at least 3 characters"
    )
    String category
  ) {}

  private record ResPresenter(String id) {}

  @PostMapping("")
  public ResponseEntity<Object> create(@Valid @RequestBody ReqBody body) {
    try {
      var createCourseRequestDTO = CreateCourseRequestDTO
        .builder()
        .name(body.name())
        .category(body.category())
        .build();

      var createCourseResponseDTO =
        this.createCourseUseCase.execute(createCourseRequestDTO);

      var presenter = new ResPresenter(
        createCourseResponseDTO.course().getId().toString()
      );

      return ResponseEntity.status(HttpStatus.CREATED).body(presenter);
    } catch (CourseAlreadyExistsError error) {
      return ResponseEntity
        .badRequest()
        .body(new ErrorMessageDTO(error.getMessage(), "name"));
    }
  }
}
