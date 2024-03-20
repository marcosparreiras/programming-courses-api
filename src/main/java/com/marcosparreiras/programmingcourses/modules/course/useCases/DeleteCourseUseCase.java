package com.marcosparreiras.programmingcourses.modules.course.useCases;

import com.marcosparreiras.programmingcourses.exceptions.CourseNotFoundError;
import com.marcosparreiras.programmingcourses.modules.course.dtos.DeleteCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.repositoires.CourseRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public void execute(DeleteCourseRequestDTO deleteCourseRequestDTO)
    throws CourseNotFoundError {
    var course =
      this.courseRepository.findById(
          UUID.fromString(deleteCourseRequestDTO.id())
        )
        .orElseThrow(() -> {
          throw new CourseNotFoundError();
        });
    this.courseRepository.delete(course);
  }
}
