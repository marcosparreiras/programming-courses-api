package com.marcosparreiras.programmingcourses.modules.course.controllers;

import com.marcosparreiras.programmingcourses.modules.course.entities.Course;
import com.marcosparreiras.programmingcourses.modules.course.useCases.FetchCourses.FetchCoursesUseCase;
import com.marcosparreiras.programmingcourses.modules.course.useCases.FetchCourses.dtos.FetchCoursesRequestDTO;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class FetchCoursesController {

  @Autowired
  private FetchCoursesUseCase fetchCoursesUseCase;

  private record ResPresenter(int total, List<Course> courses) {}

  @GetMapping("")
  public ResponseEntity<Object> index(@RequestParam Map<String, String> query) {
    var fetchCoursesRequestDTO = FetchCoursesRequestDTO
      .builder()
      .name(query.get("name"))
      .category(query.get("category"))
      .build();
    var fetchCoursesResponseDTO =
      this.fetchCoursesUseCase.execute(fetchCoursesRequestDTO);

    var presenter = new ResPresenter(
      fetchCoursesResponseDTO.courses().size(),
      fetchCoursesResponseDTO.courses()
    );

    return ResponseEntity.ok().body(presenter);
  }
}
