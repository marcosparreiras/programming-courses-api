package com.marcosparreiras.programmingcourses.modules.course.useCases;

import com.marcosparreiras.programmingcourses.exceptions.CourseNotFoundError;
import com.marcosparreiras.programmingcourses.modules.course.dtos.ToggleCourseIsActiveRequest;
import com.marcosparreiras.programmingcourses.modules.course.entities.Course;
import com.marcosparreiras.programmingcourses.modules.course.repositoires.CourseRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToggleCourseIsActiveUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public Course execute(
    ToggleCourseIsActiveRequest toggleCourseIsActiveRequest
  ) throws CourseNotFoundError {
    var course =
      this.courseRepository.findById(
          UUID.fromString(toggleCourseIsActiveRequest.id())
        )
        .orElseThrow(() -> {
          throw new CourseNotFoundError();
        });

    course.setActive(!course.isActive());
    this.courseRepository.save(course);

    return course;
  }
}
