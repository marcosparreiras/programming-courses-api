package com.marcosparreiras.programmingcourses.modules.course.useCases;

import com.marcosparreiras.programmingcourses.exceptions.CourseNotFoundError;
import com.marcosparreiras.programmingcourses.modules.course.dtos.GetCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.entities.Course;
import com.marcosparreiras.programmingcourses.modules.course.repositoires.CourseRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public Course execute(GetCourseRequestDTO getCourseRequestDTO)
    throws CourseNotFoundError {
    var course =
      this.courseRepository.findById(UUID.fromString(getCourseRequestDTO.id()))
        .orElseThrow(() -> {
          throw new CourseNotFoundError();
        });
    return course;
  }
}
