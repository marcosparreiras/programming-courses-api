package com.marcosparreiras.programmingcourses.modules.course.useCases;

import com.marcosparreiras.programmingcourses.exceptions.CourseNotFoundError;
import com.marcosparreiras.programmingcourses.modules.course.dtos.UpdateCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.entities.Course;
import com.marcosparreiras.programmingcourses.modules.course.repositoires.CourseRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public Course execute(UpdateCourseRequestDTO updateCourseRequestDTO)
    throws CourseNotFoundError {
    var course =
      this.courseRepository.findById(
          UUID.fromString(updateCourseRequestDTO.id())
        )
        .orElseThrow(() -> {
          throw new CourseNotFoundError();
        });

    if (updateCourseRequestDTO.name() != null) {
      course.setName(updateCourseRequestDTO.name());
    }

    if (updateCourseRequestDTO.category() != null) {
      course.setCategory(updateCourseRequestDTO.category());
    }

    this.courseRepository.save(course);

    return course;
  }
}
