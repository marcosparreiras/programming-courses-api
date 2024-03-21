package com.marcosparreiras.programmingcourses.modules.course.useCases.getCourse;

import com.marcosparreiras.programmingcourses.exceptions.CourseNotFoundError;
import com.marcosparreiras.programmingcourses.modules.course.repositoires.CourseRepository;
import com.marcosparreiras.programmingcourses.modules.course.useCases.getCourse.dtos.GetCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.useCases.getCourse.dtos.GetCourseResponseDTO;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public GetCourseResponseDTO execute(GetCourseRequestDTO getCourseRequestDTO)
    throws CourseNotFoundError {
    var course =
      this.courseRepository.findById(UUID.fromString(getCourseRequestDTO.id()))
        .orElseThrow(() -> {
          throw new CourseNotFoundError();
        });
    return new GetCourseResponseDTO(course);
  }
}
