package com.marcosparreiras.programmingcourses.modules.course.controllers;

import com.marcosparreiras.programmingcourses.exceptions.CourseAlreadyExistsError;
import com.marcosparreiras.programmingcourses.exceptions.CourseNotFoundError;
import com.marcosparreiras.programmingcourses.exceptions.dtos.ErrorMessageDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.CreateCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.CreateCourseResponseDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.FetchCoursesRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.FetchCoursesResponseDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.GetCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.UpdateCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.useCases.CreateCourseUseCase;
import com.marcosparreiras.programmingcourses.modules.course.useCases.FetchCoursesUseCase;
import com.marcosparreiras.programmingcourses.modules.course.useCases.GetCourseUseCase;
import com.marcosparreiras.programmingcourses.modules.course.useCases.UpdateCourseUseCase;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

  @Autowired
  private CreateCourseUseCase createCourseUseCase;

  @Autowired
  private FetchCoursesUseCase fetchCoursesUseCase;

  @Autowired
  private GetCourseUseCase getCourseUseCase;

  @Autowired
  private UpdateCourseUseCase updateCourseUseCase;

  @PostMapping("")
  public ResponseEntity<Object> create(
    @Valid @RequestBody CreateCourseRequestDTO createCourseRequestDTO
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

  @GetMapping("")
  public ResponseEntity<Object> index(@RequestParam Map<String, String> query) {
    var fetchCoursesRequestDTO = new FetchCoursesRequestDTO(
      query.get("name"),
      query.get("category")
    );
    var courses = this.fetchCoursesUseCase.execute(fetchCoursesRequestDTO);
    return ResponseEntity.ok().body(new FetchCoursesResponseDTO(courses));
  }

  @GetMapping("/{courseId}")
  public ResponseEntity<Object> show(@PathVariable String courseId) {
    try {
      var getCourseRequestDTO = GetCourseRequestDTO
        .builder()
        .id(courseId)
        .build();
      var course = this.getCourseUseCase.execute(getCourseRequestDTO);
      return ResponseEntity.ok().body(course);
    } catch (CourseNotFoundError error) {
      return ResponseEntity
        .badRequest()
        .body(new ErrorMessageDTO(error.getMessage(), null));
    }
  }

  @PutMapping("/{courseId}")
  public ResponseEntity<Object> update(
    @PathVariable String courseId,
    @RequestBody Map<String, String> body
  ) {
    try {
      var updateCourseRequestDTO = UpdateCourseRequestDTO
        .builder()
        .id(courseId)
        .name(body.get("name"))
        .category(body.get("category"))
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
