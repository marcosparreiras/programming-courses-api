package com.marcosparreiras.programmingcourses.modules.course.useCases;

import com.marcosparreiras.programmingcourses.exceptions.CourseAlreadyExistsError;
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
    throws CourseNotFoundError, CourseAlreadyExistsError {
    var course =
      this.courseRepository.findById(
          UUID.fromString(updateCourseRequestDTO.id())
        )
        .orElseThrow(() -> {
          throw new CourseNotFoundError();
        });

    if (updateCourseRequestDTO.name() != null) {
      var courseAlreadyExists =
        this.courseRepository.findByName(updateCourseRequestDTO.name());
      if (
        courseAlreadyExists != null &&
        courseAlreadyExists.getId() != course.getId()
      ) {
        throw new CourseAlreadyExistsError();
      }
      course.setName(updateCourseRequestDTO.name());
    }

    if (updateCourseRequestDTO.category() != null) {
      course.setCategory(updateCourseRequestDTO.category());
    }

    this.courseRepository.save(course);

    return course;
  }
}
