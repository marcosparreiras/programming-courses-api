package com.marcosparreiras.programmingcourses.modules.course.useCases.toggleIsActiveCourse;

import com.marcosparreiras.programmingcourses.modules.course.exceptions.CourseNotFoundError;
import com.marcosparreiras.programmingcourses.modules.course.repositoires.CourseRepository;
import com.marcosparreiras.programmingcourses.modules.course.useCases.toggleIsActiveCourse.dtos.ToggleIsActiveCourseRequest;
import com.marcosparreiras.programmingcourses.modules.course.useCases.toggleIsActiveCourse.dtos.ToggleIsActiveCourseResponse;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToggleIsActiveCourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public ToggleIsActiveCourseResponse execute(
    ToggleIsActiveCourseRequest toggleCourseIsActiveRequest
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

    return new ToggleIsActiveCourseResponse(course);
  }
}
