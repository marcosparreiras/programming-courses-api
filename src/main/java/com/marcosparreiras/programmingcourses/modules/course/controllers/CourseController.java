package com.marcosparreiras.programmingcourses.modules.course.controllers;

import com.marcosparreiras.programmingcourses.exceptions.CourseNotFoundError;
import com.marcosparreiras.programmingcourses.exceptions.dtos.ErrorMessageDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.DeleteCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.FetchCoursesRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.FetchCoursesResponseDTO;
import com.marcosparreiras.programmingcourses.modules.course.dtos.ToggleCourseIsActiveRequest;
import com.marcosparreiras.programmingcourses.modules.course.dtos.UpdateCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.useCases.DeleteCourseUseCase;
import com.marcosparreiras.programmingcourses.modules.course.useCases.FetchCoursesUseCase;
import com.marcosparreiras.programmingcourses.modules.course.useCases.ToggleCourseIsActiveUseCase;
import com.marcosparreiras.programmingcourses.modules.course.useCases.UpdateCourseUseCase;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

  @Autowired
  private FetchCoursesUseCase fetchCoursesUseCase;

  @Autowired
  private UpdateCourseUseCase updateCourseUseCase;

  @Autowired
  private DeleteCourseUseCase deleteCourseUseCase;

  @Autowired
  private ToggleCourseIsActiveUseCase toggleCourseIsActiveUseCase;

  @GetMapping("")
  public ResponseEntity<Object> index(@RequestParam Map<String, String> query) {
    var fetchCoursesRequestDTO = new FetchCoursesRequestDTO(
      query.get("name"),
      query.get("category")
    );
    var courses = this.fetchCoursesUseCase.execute(fetchCoursesRequestDTO);
    return ResponseEntity.ok().body(new FetchCoursesResponseDTO(courses));
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
